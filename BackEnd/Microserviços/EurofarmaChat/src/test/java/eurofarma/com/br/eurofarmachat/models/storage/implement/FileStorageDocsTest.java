package eurofarma.com.br.eurofarmachat.models.storage.implement;

import eurofarma.com.br.eurofarmachat.config.FileStorageProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileStorageDocsTest {

    private FileStorageDocs fileStorageDocs;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @BeforeEach
    void setUp() {
        Path pathTofolder = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        fileStorageDocs = new FileStorageDocs(pathTofolder);
    }
    @Test
    void save() {

    }

    @Test
    void listAll() throws IOException {
        List<String> files = fileStorageDocs.listAll();
        assertNotNull(files);
    }
    @Test
    void delete() {
    }

    @Test
    void getPathOfStorage() {
        Path storagePath = fileStorageDocs.getPathOfStorage();
        Path pathTofolder = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        assertEquals(pathTofolder,storagePath);
    }

    @Test
    void getDownloadUrl() {

    }

    @Test
    void getDowloadLink() {
    }
}