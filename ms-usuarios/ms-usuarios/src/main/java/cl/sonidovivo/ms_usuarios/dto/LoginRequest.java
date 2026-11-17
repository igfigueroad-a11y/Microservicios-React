package cl.sonidovivo.ms_usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Credenciales para iniciar sesión")
public class LoginRequest {

    @Schema(description = "Nombre de usuario registrado", example = "admin")
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    private String nombre;

    @Schema(description = "Contraseña del usuario", example = "admin123")
    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;
}