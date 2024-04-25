package eurofarma.com.br.eurofarmachat.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "embeddingfolder")
public class EmbeddingModelProperties {
    private String embeddingFolder;

    public String getEmbeddingFolder() {
        return embeddingFolder;
    }

    public void setEmbeddingFolder(String embeddingFolder) {
        this.embeddingFolder = embeddingFolder;
    }
}
