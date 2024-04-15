package eurofarma.com.br.eurofarmachat.services;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import eurofarma.com.br.eurofarmachat.factorys.EmbeddingStoreFactory;
import eurofarma.com.br.eurofarmachat.util.GetPath;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;


import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.*;

@Service
public class DocumentsService implements FileStorage {

    private final Path root = GetPath.toPath("/documents/", this.getClass());

    @Override
    public void saveToChatCompliance(MultipartFile file) {
        try{
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
        try{
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
        vectorSave(fileName,"eurocompliance");
    }

    @Override
    public void loadToChatEuroData(String fileName) {
        vectorSave(fileName,"eurobot");
    }

    private void vectorSave(String filename,String vectorIndex) {
        EmbeddingStoreFactory embeddingStoreFactory = new EmbeddingStoreFactory();
        EmbeddingStore<TextSegment> textSegmentEmbeddingStore = embeddingStoreFactory.embeddingStore(vectorIndex);
        EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();
        Path path = GetPath.toPath(("/documents/" + filename), this.getClass());
        Document document = loadDocument(path,  new ApacheTikaDocumentParser());
        DocumentSplitter splitter = DocumentSplitters.recursive(250, 0);
        List<TextSegment> segments = splitter.split(document);
        List<Embedding> embedding = embeddingModel.embedAll(segments).content();
        textSegmentEmbeddingStore.addAll(embedding, segments);
    }
}
