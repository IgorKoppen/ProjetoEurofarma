package eurofarma.com.br.eurofarmachat.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorage {

    void saveToChatCompliance(MultipartFile file);

    void saveToChatEuroData(MultipartFile file);

    void loadToChatCompliance(String fileName);

    void loadToChatEuroData(String fileName);
}
