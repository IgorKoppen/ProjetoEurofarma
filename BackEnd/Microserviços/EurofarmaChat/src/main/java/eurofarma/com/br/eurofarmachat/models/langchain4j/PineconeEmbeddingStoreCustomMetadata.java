package eurofarma.com.br.eurofarmachat.models.langchain4j;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.store.embedding.CosineSimilarity;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.RelevanceScore;
import io.pinecone.clients.Index;
import io.pinecone.clients.Pinecone;
import io.pinecone.proto.Vector;
import io.pinecone.unsigned_indices_model.QueryResponseWithUnsignedIndices;
import io.pinecone.unsigned_indices_model.ScoredVectorWithUnsignedIndices;

import java.util.*;
import java.util.stream.Collectors;

public class PineconeEmbeddingStoreCustomMetadata implements EmbeddingStore<TextSegment> {
    private static final String DEFAULT_NAMESPACE = "default";
    private static final String DEFAULT_METADATA_TEXT_KEY = "text_segment";
    private final Index connection;
    private final String nameSpace;
    private final String metadataTextKey;

    public PineconeEmbeddingStoreCustomMetadata(String apiKey, String index, String nameSpace, String metadataTextKey) {
        Pinecone pc = new Pinecone.Builder(apiKey).build();
        this.connection = pc.getIndexConnection(index);
        this.nameSpace = nameSpace == null ? "default" : nameSpace;
        this.metadataTextKey = metadataTextKey == null ? "text_segment" : metadataTextKey;
    }

    public String add(Embedding embedding) {
        String id = Utils.randomUUID();
        this.add(id, embedding);
        return id;
    }

    public void add(String id, Embedding embedding) {
        this.addInternal(id, embedding, (TextSegment)null);
    }

    public String add(Embedding embedding, TextSegment textSegment) {
        String id = Utils.randomUUID();
        this.addInternal(id, embedding, textSegment);
        return id;
    }

    public List<String> addAll(List<Embedding> embeddings) {
        List<String> ids = embeddings.stream().map((ignored) -> {
            return Utils.randomUUID();
        }).collect(Collectors.toList());
        this.addAllInternal(ids, embeddings, null);
        return ids;
    }

    public List<String> addAll(List<Embedding> embeddings, List<TextSegment> textSegments) {
        List<String> ids = embeddings.stream().map((ignored) -> {
            return Utils.randomUUID();
        }).collect(Collectors.toList());
        this.addAllInternal(ids, embeddings, textSegments);
        return ids;
    }

    private void addInternal(String id, Embedding embedding, TextSegment textSegment) {
        this.addAllInternal(Collections.singletonList(id), Collections.singletonList(embedding), textSegment == null ? null : Collections.singletonList(textSegment));
    }

    private void addAllInternal(List<String> ids, List<Embedding> embeddings, List<TextSegment> textSegments) {
        for(int i = 0; i < embeddings.size(); ++i) {
            String id = ids.get(i);
            Embedding embedding = embeddings.get(i);
            Struct struct = Struct.newBuilder().putFields(this.metadataTextKey, Value.newBuilder().setStringValue((textSegments.get(i)).text()).build())
                    .putFields("RelatedFile", Value.newBuilder().setStringValue(textSegments.get(i).metadata("filename")).build())
                    .build();
          this.connection.upsert(id,embedding.vectorAsList(),null,null,struct,this.nameSpace);
        }
    }

    public List<EmbeddingMatch<TextSegment>> findRelevant(Embedding referenceEmbedding, int maxResults, double minScore) {
        QueryResponseWithUnsignedIndices query = this.connection.query(maxResults, referenceEmbedding.vectorAsList(), null, null, null, this.nameSpace, null, true, true);
        List<String> matchedVectorIds = query.getMatchesList().stream().map(ScoredVectorWithUnsignedIndices::getId).collect(Collectors.toList());
        if (matchedVectorIds.isEmpty()) {
            return Collections.emptyList();
        } else {
            Collection<Vector> matchedVectors = this.connection.fetch(matchedVectorIds,this.nameSpace).getVectorsMap().values();
            List<EmbeddingMatch<TextSegment>> matches = matchedVectors.stream().map((vector) -> {
                return this.toEmbeddingMatch(vector, referenceEmbedding);
            }).filter((match) -> {
                return match.score() >= minScore;
            }).sorted(Comparator.comparingDouble(EmbeddingMatch::score)).collect(Collectors.toList());
            Collections.reverse(matches);
            return matches;
        }
    }

    private EmbeddingMatch<TextSegment> toEmbeddingMatch(Vector vector, Embedding referenceEmbedding) {
        Value textSegmentValue = vector.getMetadata().getFieldsMap().get(this.metadataTextKey);
        Value relatedFileValue = vector.getMetadata().getFieldsMap().get("RelatedFile");
        Embedding embedding = Embedding.from(vector.getValuesList());
        double cosineSimilarity = CosineSimilarity.between(embedding, referenceEmbedding);
        return new EmbeddingMatch<>(RelevanceScore.fromCosineSimilarity(cosineSimilarity), vector.getId(), embedding, textSegmentValue == null ? null : TextSegment.from(textSegmentValue.getStringValue() + " Documento Relacionado: "+ relatedFileValue.getStringValue()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String apiKey;

        private String index;
        private String nameSpace;
        private String metadataTextKey;

        public Builder() {
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder index(String index) {
            this.index = index;
            return this;
        }

        public Builder nameSpace(String nameSpace) {
            this.nameSpace = nameSpace;
            return this;
        }

        public Builder metadataTextKey(String metadataTextKey) {
            this.metadataTextKey = metadataTextKey;
            return this;
        }

        public PineconeEmbeddingStoreCustomMetadata build() {
            return new PineconeEmbeddingStoreCustomMetadata(this.apiKey, this.index, this.nameSpace, this.metadataTextKey);
        }
    }
}
