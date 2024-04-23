package eurofarma.com.br.eurofarmachat.services;

import eurofarma.com.br.eurofarmachat.configuration.FileStorageProperties;
import eurofarma.com.br.eurofarmachat.services.interfaces.FileStorage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class UploadDocumentsService implements FileStorage {

    private final Path rootToCompliance;
    private final Path rootToEuroData;


    public UploadDocumentsService(FileStorageProperties fileStorageProperties) {
        this.rootToCompliance = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize().resolve("compliance");
        this.rootToEuroData = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize().resolve("eurodata");
    }

    @Override
    public void saveToChatCompliance(MultipartFile file) {
        UploadFile(file, rootToCompliance);
    }

    @Override
    public void saveToChatEuroData(MultipartFile file) {
        UploadFile(file, rootToEuroData);
    }

    private void UploadFile(MultipartFile file, Path root) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path targetLocation = root.resolve(fileName);
            file.transferTo(targetLocation);
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file of that name already exists.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while transferring the file.", e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
