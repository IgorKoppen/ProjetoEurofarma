package eurofarma.com.br.eurofarmachat.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetPath {

    public static Path toPath(String path, Class<?> classTarget) {
        try {
            URL fileUrl = classTarget.getResource(path);
            assert fileUrl != null;
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
