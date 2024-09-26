package br.com.connectfy.EurofarmaCliente.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableConfigurationProperties({WebConfigProperties.class})
public class WebConfig implements WebMvcConfigurer {

    private final WebConfigProperties webConfigProperties;

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    public WebConfig(WebConfigProperties webConfigProperties) {
        this.webConfigProperties = webConfigProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebConfigProperties.Cors cors = webConfigProperties.getCors();
        registry.addMapping("/**")
                .allowedOrigins(cors.getAllowedOrigins())
                .allowedMethods(cors.getAllowedMethods())
                .maxAge(cors.getMaxAge())
                .allowedHeaders(cors.getAllowedHeaders())
                .exposedHeaders(cors.getExposedHeaders());
    }
}
