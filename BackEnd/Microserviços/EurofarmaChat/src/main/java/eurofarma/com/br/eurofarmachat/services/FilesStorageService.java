package eurofarma.com.br.eurofarmachat.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FilesStorageService {

  List<String> listAll();

  ResponseEntity<String> save(MultipartFile file);

  ResponseEntity<String> delete(String filename);

  ResponseEntity<Resource> getDownloadLink(String filename, HttpServletRequest request);

}
