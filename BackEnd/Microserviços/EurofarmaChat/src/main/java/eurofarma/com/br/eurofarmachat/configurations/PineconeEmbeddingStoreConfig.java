package eurofarma.com.br.eurofarmachat.configurations;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PineconeEmbeddingStoreConfig {

    @Value("${pinecone.environment}")
    private String environment;

    @Value("${pinecone.projectId}")
    private String projectId;

    @Value("${pinecone.index}")
    private String index;


    @Value("${pinecone.apikey}")
    private String apikey;

    private PineconeEmbeddingStore store;


    @Bean
    public EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }


    @Bean
    public EmbeddingStoreContentRetriever embeddingStoreContentRetriever() {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel())
                .embeddingStore(embeddingStore()).maxResults(5).minScore(0.75)
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        this.store = PineconeEmbeddingStore.builder()
                .apiKey(apikey)
                .environment(environment)
                .projectId(projectId)
                .index(index)
                .build();
        return this.store;
    }

}


