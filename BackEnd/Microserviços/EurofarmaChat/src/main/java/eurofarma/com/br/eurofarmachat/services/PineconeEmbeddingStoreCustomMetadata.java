package eurofarma.com.br.eurofarmachat.services;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.store.embedding.CosineSimilarity;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.RelevanceScore;
import io.pinecone.PineconeClient;
import io.pinecone.PineconeClientConfig;
import io.pinecone.PineconeConnection;
import io.pinecone.PineconeConnectionConfig;
import io.pinecone.proto.FetchRequest;
import io.pinecone.proto.QueryRequest;
import io.pinecone.proto.ScoredVector;
import io.pinecone.proto.UpsertRequest;
import io.pinecone.proto.Vector;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PineconeEmbeddingStoreCustomMetadata implements EmbeddingStore<TextSegment> {
    private static final String DEFAULT_NAMESPACE = "default";
    private static final String DEFAULT_METADATA_TEXT_KEY = "text_segment";
    private final PineconeConnection connection;
    private final String nameSpace;
    private final String metadataTextKey;

    public PineconeEmbeddingStoreCustomMetadata(String apiKey, String environment, String projectId, String index, String nameSpace, String metadataTextKey) {
        PineconeClientConfig configuration = (new PineconeClientConfig()).withApiKey(apiKey).withEnvironment(environment).withProjectName(projectId);
        PineconeClient pineconeClient = new PineconeClient(configuration);
        PineconeConnectionConfig connectionConfig = (new PineconeConnectionConfig()).withIndexName(index);
        this.connection = pineconeClient.connect(connectionConfig);
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
        List<String> ids = (List)embeddings.stream().map((ignored) -> {
            return Utils.randomUUID();
        }).collect(Collectors.toList());
        this.addAllInternal(ids, embeddings, (List)null);
        return ids;
    }

    public List<String> addAll(List<Embedding> embeddings, List<TextSegment> textSegments) {
        List<String> ids = (List)embeddings.stream().map((ignored) -> {
            return Utils.randomUUID();
        }).collect(Collectors.toList());
        this.addAllInternal(ids, embeddings, textSegments);
        return ids;
    }

    private void addInternal(String id, Embedding embedding, TextSegment textSegment) {
        this.addAllInternal(Collections.singletonList(id), Collections.singletonList(embedding), textSegment == null ? null : Collections.singletonList(textSegment));
    }

    private void addAllInternal(List<String> ids, List<Embedding> embeddings, List<TextSegment> textSegments) {
        UpsertRequest.Builder upsertRequestBuilder = UpsertRequest.newBuilder().setNamespace(this.nameSpace);
        for(int i = 0; i < embeddings.size(); ++i) {
            String id = (String)ids.get(i);
            Embedding embedding = (Embedding)embeddings.get(i);
            Vector.Builder vectorBuilder = Vector.newBuilder().setId(id).addAllValues(embedding.vectorAsList());
            Struct struct = Struct.newBuilder().putFields(this.metadataTextKey, Value.newBuilder().setStringValue(((TextSegment) textSegments.get(i)).text()).build())
                    .putFields("RelatedFile", Value.newBuilder().setStringValue(textSegments.get(i).metadata("filename")).build())
                    .build();
            vectorBuilder.setMetadata(struct);
            upsertRequestBuilder.addVectors(vectorBuilder.build());
        }
        this.connection.getBlockingStub().upsert(upsertRequestBuilder.build());
    }

    public List<EmbeddingMatch<TextSegment>> findRelevant(Embedding referenceEmbedding, int maxResults, double minScore) {
        QueryRequest queryRequest = QueryRequest.newBuilder().addAllVector(referenceEmbedding.vectorAsList()).setNamespace(this.nameSpace).setTopK(maxResults).build();
        List<String> matchedVectorIds = (List)this.connection.getBlockingStub().query(queryRequest).getMatchesList().stream().map(ScoredVector::getId).collect(Collectors.toList());
        if (matchedVectorIds.isEmpty()) {
            return Collections.emptyList();
        } else {
            Collection<Vector> matchedVectors = this.connection.getBlockingStub().fetch(FetchRequest.newBuilder().addAllIds(matchedVectorIds).setNamespace(this.nameSpace).build()).getVectorsMap().values();
            List<EmbeddingMatch<TextSegment>> matches = (List)matchedVectors.stream().map((vector) -> {
                return this.toEmbeddingMatch(vector, referenceEmbedding);
            }).filter((match) -> {
                return match.score() >= minScore;
            }).sorted(Comparator.comparingDouble(EmbeddingMatch::score)).collect(Collectors.toList());
            Collections.reverse(matches);
            return matches;
        }
    }

    private EmbeddingMatch<TextSegment> toEmbeddingMatch(Vector vector, Embedding referenceEmbedding) {
        Value textSegmentValue = (Value)vector.getMetadata().getFieldsMap().get(this.metadataTextKey);
        Embedding embedding = Embedding.from(vector.getValuesList());
        double cosineSimilarity = CosineSimilarity.between(embedding, referenceEmbedding);
        return new EmbeddingMatch(RelevanceScore.fromCosineSimilarity(cosineSimilarity), vector.getId(), embedding, textSegmentValue == null ? null : TextSegment.from(textSegmentValue.getStringValue()));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String apiKey;
        private String environment;
        private String projectId;
        private String index;
        private String nameSpace;
        private String metadataTextKey;

        public Builder() {
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder environment(String environment) {
            this.environment = environment;
            return this;
        }

        public Builder projectId(String projectId) {
            this.projectId = projectId;
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
            return new PineconeEmbeddingStoreCustomMetadata(this.apiKey, this.environment, this.projectId, this.index, this.nameSpace, this.metadataTextKey);
        }
    }
}
