package eurofarma.com.br.eurofarmachat.services.interfaces;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesStorageService {

 public List<String> listAll();

 public ResponseEntity<String> save(MultipartFile file);

 public ResponseEntity<String> delete(String filename);

 public ResponseEntity<Resource> getDownload(String filename, HttpServletRequest request);

}
