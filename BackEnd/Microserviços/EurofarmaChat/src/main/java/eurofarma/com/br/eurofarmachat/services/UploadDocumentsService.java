package eurofarma.com.br.eurofarmachat.services;

import eurofarma.com.br.eurofarmachat.services.interfaces.FileStorage;
import eurofarma.com.br.eurofarmachat.util.GetPath;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class UploadDocumentsService implements FileStorage {

    private final Path rootToCompliance = GetPath.toPath("/static/documents/compliance/", this.getClass());;
    private final Path rootToEuroData = GetPath.toPath("/static/documents/eurodata/", this.getClass());

    @Override
    public void saveToChatCompliance(MultipartFile file) {
        UploadFile(file, rootToCompliance);
    }

    @Override
    public void saveToChatEuroData(MultipartFile file) {
        UploadFile(file, rootToEuroData);
    }

    private void UploadFile(MultipartFile file, Path rootToCompliance) {
        try {
            file.transferTo(rootToCompliance.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException("A file of that name already exists.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while transferring the file.", e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
