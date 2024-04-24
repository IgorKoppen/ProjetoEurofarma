package eurofarma.com.br.eurofarmachat.models.langchain4j;
import dev.langchain4j.model.embedding.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.PoolingMode;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import eurofarma.com.br.eurofarmachat.util.GetPath;


public class EmbeddingStoreContentRetrieverCreater {


   private final ContentRetriever retriever;

    public EmbeddingStoreContentRetrieverCreater(String index) {
        this.retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(new OnnxEmbeddingModel(GetPath.toPath("/static/embeddingModel/multilingual-e5-small//onnx//model.onnx",this.getClass()),GetPath.toPath("/static/embeddingModel/multilingual-e5-small//onnx//tokenizer.json",this.getClass()), PoolingMode.MEAN))
                .embeddingStore(new EmbeddingStoreCreater(index).getPineconeEmbeddingStoreCustomMetadata())
                .maxResults(5)
                .minScore(0.8)
                .build();

    }

    public ContentRetriever getRetriever() {
        return retriever;
    }
}
