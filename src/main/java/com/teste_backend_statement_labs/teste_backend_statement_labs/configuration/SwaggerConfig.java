package com.teste_backend_statement_labs.teste_backend_statement_labs.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI parkingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Estacionamento")
                        .description("Gerenciamento de entradas, saídas e histórico de veículos")
                        .version("v1.0"));
    }
}
