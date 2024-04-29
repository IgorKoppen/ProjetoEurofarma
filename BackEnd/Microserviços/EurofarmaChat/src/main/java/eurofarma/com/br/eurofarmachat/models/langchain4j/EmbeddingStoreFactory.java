package eurofarma.com.br.eurofarmachat.models.langchain4j;

public class EmbeddingStoreFactory {

    public static PineconeEmbeddingStoreCustomMetadata createPineconeEmbeddingStoreCustomMetadata(String index) {
        return PineconeEmbeddingStoreCustomMetadata.builder()
                .apiKey("a0d185f6-a92e-4d14-ac9f-c7d018fe5309")
                .index(index)
                .build();
    }
}
