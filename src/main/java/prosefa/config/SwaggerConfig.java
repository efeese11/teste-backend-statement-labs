package prosefa.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PROSEFA - Programa de Selos Fiscais de Alta Segurança",
                version = "1.0",
                description = "API para registro de empresas, solicitação e validação de selos fiscais, com auditoria e autenticação JWT.",
                contact = @Contact(
                        name = "Statement Labs",
                        email = "contato@statementlabs.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        )
)
public class SwaggerConfig {
}