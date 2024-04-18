package eurofarma.com.br.eurofarmachat.models;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;


public class EmbeddingStoreContentRetriever {

    public dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever embeddingStoreContentRetriever(String index) {
        return dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever.builder()
                .embeddingModel(new AllMiniLmL6V2EmbeddingModel())
                .embeddingStore(new EmbeddingStore().embeddingStore(index))
                .minScore(0.7)
                .maxResults(20)
                .build();
    }
}
