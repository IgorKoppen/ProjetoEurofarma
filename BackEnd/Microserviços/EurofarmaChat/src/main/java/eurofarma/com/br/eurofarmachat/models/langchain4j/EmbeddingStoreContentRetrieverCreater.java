package eurofarma.com.br.eurofarmachat.models.langchain4j;
import dev.langchain4j.model.embedding.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.PoolingMode;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import eurofarma.com.br.eurofarmachat.configuration.EmbeddingModelProperties;


import java.nio.file.Path;
import java.nio.file.Paths;


public class EmbeddingStoreContentRetrieverCreater {


   private final ContentRetriever retriever;
    public EmbeddingStoreContentRetrieverCreater(String index, EmbeddingModelProperties embeddingModelProperties) {
        Path embeddingPath = Paths.get(embeddingModelProperties.getEmbeddingFolder()).toAbsolutePath().normalize().resolve("multilingual-e5-small").resolve("onnx");
        Path onnxEmbeddingModelPath = embeddingPath.resolve("model.onnx");
        Path onnxTokenizerPath = embeddingPath.resolve("tokenizer.json");

        this.retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(new OnnxEmbeddingModel(onnxEmbeddingModelPath,onnxTokenizerPath, PoolingMode.MEAN))
                .embeddingStore(new EmbeddingStoreCreater(index).getPineconeEmbeddingStoreCustomMetadata())
                .maxResults(5)
                .minScore(0.8)
                .build();

    }

    public ContentRetriever getRetriever() {
        return retriever;
    }
}
