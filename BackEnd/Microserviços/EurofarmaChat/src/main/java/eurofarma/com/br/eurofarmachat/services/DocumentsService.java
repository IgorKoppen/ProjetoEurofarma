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
import eurofarma.com.br.eurofarmachat.factorys.EmbeddingStoreFactory;
import eurofarma.com.br.eurofarmachat.util.GetPath;
import io.pinecone.clients.Index;
import io.pinecone.clients.Pinecone;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.*;

@Service
public class DocumentsService implements FileStorage {

    private final Path root = GetPath.toPath("/documents/", this.getClass());

    @Override
    public void saveToChatCompliance(MultipartFile file) {
        try {
            file.transferTo(root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file of that name already exists.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while transferring the file.", e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void saveToChatEuroData(MultipartFile file) {
        try {
            file.transferTo(root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file of that name already exists.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while transferring the file.", e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void loadToChatCompliance(String fileName) {
        vectorSave(fileName, "eurocompliance");
    }

    @Override
    public void loadToChatEuroData(String fileName) {
        vectorSave(fileName, "eurobot");
    }

    private void vectorSave(String filename, String vectorIndex) {
        EmbeddingStoreFactory embeddingStoreFactory = new EmbeddingStoreFactory();
        EmbeddingStore<TextSegment> textSegmentEmbeddingStore = embeddingStoreFactory.embeddingStore(vectorIndex);
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        Path path = GetPath.toPath(("/documents/" + filename), this.getClass());
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
        Index index = pc.getIndexConnection(vectorIndex);
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
