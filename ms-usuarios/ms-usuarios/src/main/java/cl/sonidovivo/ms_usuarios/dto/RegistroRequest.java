package cl.sonidovivo.ms_usuarios.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Datos requeridos para registrar un nuevo cliente")
public class RegistroRequest {

    @Schema(description = "Nombre de usuario único", example = "ignacio_dev")
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 3, max = 50, message = "El usuario debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @Schema(description = "Correo electrónico válido", example = "ignacio@sonidovivo.cl")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El formato del correo electrónico no es válido.")
    private String email;

    @Schema(description = "Contraseña (mínimo 4 caracteres)", example = "clave1234")
    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 4, max = 100, message = "La contraseña debe tener al menos 4 caracteres.")
    private String password;
}