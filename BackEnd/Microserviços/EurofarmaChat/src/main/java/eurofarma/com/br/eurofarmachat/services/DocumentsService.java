package eurofarma.com.br.eurofarmachat.services;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
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
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public DocumentsService(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    @Override
    public void save(MultipartFile file) {
        try{
            file.transferTo(root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            vectorSave(file.getOriginalFilename());
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file of that name already exists.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while transferring the file.", e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    private void vectorSave(String filename) {
        Path path = GetPath.toPath(("/documents/" + filename), this.getClass());
        Document document = loadDocument(path, new ApachePdfBoxDocumentParser());
        DocumentSplitter splitter = DocumentSplitters.recursive(175, 0);
        List<TextSegment> segments = splitter.split(document);
        List<Embedding> embedding = embeddingModel.embedAll(segments).content();
        embeddingStore.addAll(embedding, segments);
    }

    public void load(MultipartFile file) {
        vectorSave(file.getOriginalFilename());
    }
}
