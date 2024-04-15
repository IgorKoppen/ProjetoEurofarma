package eurofarma.com.br.eurofarmachat.factorys;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;

public class EmbeddingStoreFactory {

    public EmbeddingStore<TextSegment> embeddingStore(String index) {
        return PineconeEmbeddingStore.builder()
                .apiKey("a0d185f6-a92e-4d14-ac9f-c7d018fe5309")
                .environment("aped-4627-b74a")
                .projectId("k0g5zi8")
                .index(index)
                .build();
    }

}
