package eurofarma.com.br.eurofarmachat.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetPath {
    public static Path toPath(String path, Class<?> classTarget) {
        URL fileUrl = classTarget.getResource(path);
        if (fileUrl == null) {
            try {
                URL rootUrl = classTarget.getResource("/");
                Path absolutePath = Paths.get(new URI(rootUrl + path));
                createDirectoryIfNotExists(absolutePath);
                fileUrl = classTarget.getResource(path);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createDirectoryIfNotExists(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Could not create directory at " + path, e);
            }
        }
    }
}
