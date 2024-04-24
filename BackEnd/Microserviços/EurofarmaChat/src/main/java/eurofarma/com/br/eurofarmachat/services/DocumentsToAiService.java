package eurofarma.com.br.eurofarmachat.services;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingStore;
import eurofarma.com.br.eurofarmachat.configuration.FileStorageProperties;
import eurofarma.com.br.eurofarmachat.models.langchain4j.EmbeddingStoreCreater;
import eurofarma.com.br.eurofarmachat.util.GetPath;
import io.pinecone.clients.AsyncIndex;
import io.pinecone.clients.Pinecone;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@Service
public class DocumentsToAiService {


    public void loadToPineconeWithIndex(String fileName,String indexName,Path pathToFolder) {
        vectorSave(fileName,indexName, pathToFolder);
    }

    public void deleteAtPinecoWithIndex(String fileName,String indexName) {
        vectorDeleter(fileName, indexName);
    }


    private void vectorSave(String filename, String vectorIndex,Path path) {
        EmbeddingStoreCreater embeddingStore = new EmbeddingStoreCreater(vectorIndex);
        EmbeddingStore<TextSegment> textSegmentEmbeddingStore = embeddingStore.getPineconeEmbeddingStoreCustomMetadata();
        EmbeddingModel embeddingModel = new OnnxEmbeddingModel(GetPath.toPath("/static/embeddingModel/multilingual-e5-small//onnx//model.onnx", this.getClass()), GetPath.toPath("/static/embeddingModel/multilingual-e5-small//onnx//tokenizer.json", this.getClass()), PoolingMode.MEAN);
        List<TextSegment> segments = splitDocumentInSmallerPices(path);
        List<TextSegment> segmentsWithMetadata = putMetadataFileName(segments, filename);
        List<Embedding> embedding = embeddingModel.embedAll(segmentsWithMetadata).content();
        textSegmentEmbeddingStore.addAll(embedding, segmentsWithMetadata);
    }

    private Path getPath(String fileName,Path path) {
        return path.resolve(fileName);
    }


    private List<TextSegment> splitDocumentInSmallerPices(Path path) {
        Document document = loadDocument(path, new ApacheTikaDocumentParser());
        DocumentSplitter splitter = DocumentSplitters.recursive(400, 200);
        return splitter.split(document);
    }

    private List<TextSegment> putMetadataFileName(List<TextSegment> segments, String filename) {
        List<TextSegment> segmentsWithMetadata = new ArrayList<>();
        for (TextSegment textSegment : segments) {
            Metadata metadata = new Metadata();
            metadata.add("filename", filename);
            String cleanedText = textSegment.text().replace("\n", " ").replace("\t", " ");
            TextSegment textSegmentWithMetadata = TextSegment.from(cleanedText, metadata);
            segmentsWithMetadata.add(textSegmentWithMetadata);
        }
        return segmentsWithMetadata;
    }

    private void vectorDeleter(String filename, String vectorIndex) {
        Pinecone pc = new Pinecone.Builder("a0d185f6-a92e-4d14-ac9f-c7d018fe5309").build();
        AsyncIndex index = pc.getAsyncIndexConnection(vectorIndex);
        Struct filter = Struct.newBuilder()
                .putFields("RelatedFile", Value.newBuilder()
                        .setStructValue(Struct.newBuilder()
                                .putFields("$eq", Value.newBuilder()
                                        .setStringValue(filename)
                                        .build()))
                        .build())
                .build();
        index.deleteByFilter(filter, "default");
    }
}
