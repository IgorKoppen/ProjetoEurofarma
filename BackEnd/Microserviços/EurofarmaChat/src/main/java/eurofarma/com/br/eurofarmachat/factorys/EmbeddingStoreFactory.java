package eurofarma.com.br.eurofarmachat.factorys;
import eurofarma.com.br.eurofarmachat.services.PineconeEmbeddingStoreCustomMetadata;

public class EmbeddingStoreFactory {

    public PineconeEmbeddingStoreCustomMetadata embeddingStore(String index) {
        return PineconeEmbeddingStoreCustomMetadata.builder()
                .apiKey("a0d185f6-a92e-4d14-ac9f-c7d018fe5309")
                .environment("aped-4627-b74a")
                .projectId("k0g5zi8")
                .index(index)
                .build();
    }

}
