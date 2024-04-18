package eurofarma.com.br.eurofarmachat.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {

    void saveToChatCompliance(MultipartFile file);

    void saveToChatEuroData(MultipartFile file);

}
