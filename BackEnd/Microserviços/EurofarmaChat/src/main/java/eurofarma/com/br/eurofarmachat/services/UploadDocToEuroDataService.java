package eurofarma.com.br.eurofarmachat.services;

import eurofarma.com.br.eurofarmachat.configuration.FileStorageProperties;
import eurofarma.com.br.eurofarmachat.services.interfaces.FilesStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UploadDocToEuroDataService implements FilesStorageService {

    private final Path pathToEuroData;
    private final DocumentsToAiService documentsToAiService;
    private final String indexInPinecone = "eurobot";

    public UploadDocToEuroDataService(FileStorageProperties fileStorageProperties, DocumentsToAiService documentsToAiService) {
        this.pathToEuroData = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize().resolve("eurodata");
        this.documentsToAiService = documentsToAiService;
    }

    @Override
    public List<String> listAll() {
        List<String> lista = new ArrayList<>();
        try {
            lista = Files.list(pathToEuroData)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
            return lista;
        } catch (IOException e) {
            return lista;
        }
    }

    @Override
    public ResponseEntity<String> save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path targetLocation = pathToEuroData.resolve(fileName);
            file.transferTo(targetLocation);
            documentsToAiService.loadToPineconeWithIndex(fileName, indexInPinecone, pathToEuroData);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("File upload failed!");
        }
    }

    @Override
    public ResponseEntity<String> delete(String filename) {
        try {
            Files.delete(pathToEuroData.resolve(filename));
            documentsToAiService.deleteAtPinecoWithIndex(filename, indexInPinecone);
            return ResponseEntity.ok("File deleted successfully!");
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Resource> getDownload(String filename, HttpServletRequest request) {
        Path filePath = pathToEuroData.resolve(filename).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
