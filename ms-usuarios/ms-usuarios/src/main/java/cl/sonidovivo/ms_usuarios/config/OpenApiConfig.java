package cl.sonidovivo.ms_usuarios.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Microservicio de Usuarios - Sonido Vivo",
        version = "1.0.0",
        description = "Gestión de autenticación, registro de clientes con encriptación BCrypt y administración de roles.",
        contact = @Contact(name = "Sonido Vivo Dev", email = "soporte@sonidovivo.cl")
    ),
    servers = {
        @Server(url = "http://localhost:8081", description = "Servidor Directo (ms-usuarios)"),
        @Server(url = "http://localhost:8080", description = "A través de API Gateway")
    }
)
public class OpenApiConfig {
}