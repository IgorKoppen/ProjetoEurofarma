package eurofarma.com.br.eurofarmachat.models;

public class EmbeddingStoreCreater {

    private final PineconeEmbeddingStoreCustomMetadata pineconeEmbeddingStoreCustomMetadata;


    public EmbeddingStoreCreater(String index) {
        this.pineconeEmbeddingStoreCustomMetadata = PineconeEmbeddingStoreCustomMetadata.builder()
                .apiKey("a0d185f6-a92e-4d14-ac9f-c7d018fe5309")
                .index(index)
                .build();;
    }


    public PineconeEmbeddingStoreCustomMetadata getPineconeEmbeddingStoreCustomMetadata() {
        return pineconeEmbeddingStoreCustomMetadata;
    }
}
