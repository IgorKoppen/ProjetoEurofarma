package br.com.connectfy.EurofarmaCliente.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer")
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto EuroFarma")
                        .version("v0.0.1")
                        .description("Projeto Challenge Sprint Eurofarma")
                        .termsOfService("https://eurofarma.com.br/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/IgorKoppen/ProjetoEuroFarma")
                        )
                );
    }

}
