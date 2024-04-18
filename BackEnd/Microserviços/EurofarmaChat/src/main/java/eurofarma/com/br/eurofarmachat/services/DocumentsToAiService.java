package eurofarma.com.br.eurofarmachat.services;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import eurofarma.com.br.eurofarmachat.models.EmbeddingStoreCreater;
import eurofarma.com.br.eurofarmachat.util.GetPath;
import io.pinecone.clients.AsyncIndex;
import io.pinecone.clients.Pinecone;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@Service
public class DocumentsToAiService {

    public void loadToChatCompliance(String fileName) {
        vectorSave(fileName, "eurocompliance");
    }

    public void loadToChatEuroData(String fileName) {
        vectorSave(fileName, "eurobot");
    }

    public void deleteToChatCompliance(String fileName) {
        vectorDeleter(fileName, "eurocompliance");
    }

    public void deleteToChatEuroData(String fileName) {
        vectorDeleter(fileName,"eurobot");
    }

    private void vectorSave(String filename, String vectorIndex) {
        EmbeddingStoreCreater embeddingStore = new EmbeddingStoreCreater(vectorIndex);
        EmbeddingStore<TextSegment> textSegmentEmbeddingStore = embeddingStore.getPineconeEmbeddingStoreCustomMetadata();
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        Path path = GetPath.toPath(("/static/documents/" + filename), this.getClass());
        Document document = loadDocument(path, new ApacheTikaDocumentParser());
        DocumentSplitter splitter = DocumentSplitters.recursive(350, 30);
        List<TextSegment> segments = splitter.split(document);
        List<TextSegment> segmentsWithMetadata = new ArrayList<>();
        for (TextSegment textSegment : segments) {
            Metadata metadata = new Metadata();
            metadata.add("filename", filename);
            TextSegment textSegmentWithMetadata = TextSegment.from(textSegment.text(), metadata);
            segmentsWithMetadata.add(textSegmentWithMetadata);
        }
        List<Embedding> embedding = embeddingModel.embedAll(segmentsWithMetadata).content();
        textSegmentEmbeddingStore.addAll(embedding, segmentsWithMetadata);
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
