package eurofarma.com.br.eurofarmachat.models.storage;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface Storage {

     List<String> listAll() throws IOException;

     String save(MultipartFile file) throws IOException ;

     void delete(String filename) throws IOException;

     Path getPathOfStorage() ;

     ResponseEntity<Resource> getDowloadLink(String filename,HttpServletRequest request) throws IOException;
}
