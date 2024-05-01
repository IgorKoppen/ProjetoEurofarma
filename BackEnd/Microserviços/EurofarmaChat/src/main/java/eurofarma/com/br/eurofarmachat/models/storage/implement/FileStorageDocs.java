package eurofarma.com.br.eurofarmachat.models.storage.implement;

import eurofarma.com.br.eurofarmachat.exceptions.ArchiveTypeExeception;
import eurofarma.com.br.eurofarmachat.exceptions.MyFileNotFoundException;
import eurofarma.com.br.eurofarmachat.models.storage.Storage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class FileStorageDocs implements Storage {

    private final Path pathToStorage;
    private final List<String> filesExtensionsAllowed;



    public FileStorageDocs(Path pathToStorage, List<String> filesExtensionsAllowed) {
        this.pathToStorage = pathToStorage.normalize();
        this.filesExtensionsAllowed = filesExtensionsAllowed;
    }

    @Override
    public List<String> listAll() throws IOException {
        return Files.list(pathToStorage)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String save(MultipartFile file) throws IOException, ArchiveTypeExeception {
        if (allowedFileType(file.getOriginalFilename())) {
            throw new ArchiveTypeExeception("Extensão não permitida! " + " Extensões permitidas: " + String.join(",", filesExtensionsAllowed));
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileName = fileNameConcatenateUUID(fileName);
        Path targetLocation = pathToStorage.resolve(fileName);
        file.transferTo(targetLocation);

       return fileName;
    }

    private String fileNameConcatenateUUID(String fileName) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "-" + fileName;
    }

    @Override
    public void delete(String filename) throws IOException {
        Files.delete(pathToStorage.resolve(filename));
    }


    @Override
    public Path getPathOfStorage() {
        return pathToStorage;
    }

    @Override
    public String getDownloadUrl(String filename, String endPointOfDowload) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(endPointOfDowload)
                .path(filename)
                .toUriString();
    }

    @Override
    public ResponseEntity<Resource> getDowloadLink(String filename, HttpServletRequest request) throws IOException {
            Resource resource = loadFileAsResource(filename);
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
    }

    private Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.pathToStorage.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
            throw new MyFileNotFoundException("Could not find file " + fileName, e);
        }
    }

    private Boolean allowedFileType(String fileName) {
        boolean isRestricted = true;
        for (String extension : filesExtensionsAllowed) {
            if (fileName.endsWith(extension)) {
                isRestricted = false;
            }
        }
        return isRestricted;
    }
}

