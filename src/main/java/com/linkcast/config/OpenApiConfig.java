package com.linkcast.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI linkCastOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("LinkCast API")
                .description("API para transformação de conteúdos em episódios de podcast")
                .version("v1")
                .contact(new Contact()
                    .name("LinkCast")
                    .email("contato@linkcast.com.br"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}