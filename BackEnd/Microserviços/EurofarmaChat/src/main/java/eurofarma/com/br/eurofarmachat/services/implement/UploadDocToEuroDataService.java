package eurofarma.com.br.eurofarmachat.services.implement;

import eurofarma.com.br.eurofarmachat.configuration.FileStorageProperties;
import eurofarma.com.br.eurofarmachat.exceptions.ResourceNotFoundException;
import eurofarma.com.br.eurofarmachat.models.storage.implement.FileStorageDocs;
import eurofarma.com.br.eurofarmachat.services.FilesStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadDocToEuroDataService implements FilesStorageService {

    private final FileStorageDocs fileStorage;
    private final VectorStorePineconeService documentsToAiService;
    private final String indexInPinecone = "eurodata";

    public UploadDocToEuroDataService(FileStorageProperties fileStorageProperties, VectorStorePineconeService documentsToAiService) {
        this.fileStorage = new FileStorageDocs(Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize().resolve("eurodata"));
        this.documentsToAiService = documentsToAiService;
    }

    @Override
    public List<String> listAll() {
        List<String> lista = new ArrayList<>();
        try {
            lista = fileStorage.listAll();
            return lista;
        } catch (IOException e) {
            return lista;
        }
    }

    @Override
    public ResponseEntity<String> save(MultipartFile file) {
        try {
            String fileNameWithUUID = fileStorage.save(file);
            String endPointDowload = "euroDataDocs/download/";
            String fileDownloadUri = fileStorage.getDownloadUrl(fileNameWithUUID, endPointDowload);
            documentsToAiService.loadToPineconeWithIndex(fileNameWithUUID,fileDownloadUri, indexInPinecone, fileStorage.getPathOfStorage());
            return ResponseEntity.ok("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Arquivo falhou no upload!");
        }
    }

    @Override
    public ResponseEntity<String> delete(String filename) {
        try {
            fileStorage.delete(filename);
            documentsToAiService.deleteAtPinecoWithIndex(filename, indexInPinecone);
            return ResponseEntity.ok("Arquivo deletado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Resource> getDownloadLink(String filename, HttpServletRequest request) {
        try {
           return fileStorage.getDowloadLink(filename,request);
        } catch (IOException e) {
            throw new ResourceNotFoundException("Arquivo não encontrado!");
        }
    }
}
