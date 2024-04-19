package eurofarma.com.br.eurofarmachat.models;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;


public class EmbeddingStoreContentRetrieverCreater {


   private final EmbeddingStoreContentRetriever retriever;

    public EmbeddingStoreContentRetrieverCreater(String index) {
        this.retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(new AllMiniLmL6V2EmbeddingModel())
                .embeddingStore(new EmbeddingStoreCreater(index).getPineconeEmbeddingStoreCustomMetadata())
                .minScore(0.75)
                .maxResults(40)
                .build();;
    }

    public EmbeddingStoreContentRetriever getRetriever() {
        return retriever;
    }
}
