package eurofarma.com.br.eurofarmachat.services;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorage {

    public void save(MultipartFile file);

}
