package eurofarma.com.br.eurofarmachat.factorys;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;


public class embeddingStoreContentRetrieverFactory {

    public EmbeddingStoreContentRetriever embeddingStoreContentRetriever(String index) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(new AllMiniLmL6V2EmbeddingModel())
                .embeddingStore(new EmbeddingStoreFactory().embeddingStore(index)).maxResults(6).minScore(0.7)
                .build();
    }
}
