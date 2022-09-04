package org.openapitools.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Swagger Online Market - OpenAPI 3.0")
                                .description("-This is a sample Online Store Server based on the OpenAPI 3.0 specification.")
                                .contact(
                                        new Contact()
                                                .email("Gang32rus@mail.ru")
                                )
                                .version("1.0.0")
                )
        ;
    }
}