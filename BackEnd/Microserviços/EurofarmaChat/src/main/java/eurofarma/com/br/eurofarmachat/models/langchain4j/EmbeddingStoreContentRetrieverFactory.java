package eurofarma.com.br.eurofarmachat.models.langchain4j;

import dev.langchain4j.model.embedding.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.PoolingMode;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import java.nio.file.Paths;
import java.nio.file.Path;

public class EmbeddingStoreContentRetrieverFactory {


    public static ContentRetriever EmbeddingStoreContentRetrieverCreater(String index, String embeddingModelPropertiesFolder) {
        Path embeddingPath = Paths.get(embeddingModelPropertiesFolder).toAbsolutePath().normalize().resolve("multilingual-e5-small").resolve("onnx");
        Path onnxEmbeddingModelPath = embeddingPath.resolve("model.onnx");
        Path onnxTokenizerPath = embeddingPath.resolve("tokenizer.json");

       return EmbeddingStoreContentRetriever.builder()
                .embeddingModel(new OnnxEmbeddingModel(onnxEmbeddingModelPath,onnxTokenizerPath, PoolingMode.MEAN))
                .embeddingStore(EmbeddingStoreFactory.createPineconeEmbeddingStoreCustomMetadata(index))
                .maxResults(12)
                .minScore(0.75)
                .build();
    }

}
