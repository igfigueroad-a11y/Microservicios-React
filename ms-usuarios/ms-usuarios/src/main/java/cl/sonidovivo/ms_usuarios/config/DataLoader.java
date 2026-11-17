package cl.sonidovivo.ms_usuarios.config;

import cl.sonidovivo.ms_usuarios.model.Usuario;
import cl.sonidovivo.ms_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario(null, "admin", "admin@sonidovivo.cl", passwordEncoder.encode("admin123"), "admin");
            Usuario cliente = new Usuario(null, "usuario", "usuario@sonidovivo.cl", passwordEncoder.encode("1234"), "cliente");
            usuarioRepository.save(admin);
            usuarioRepository.save(cliente);
            System.out.println(">>> Usuarios iniciales cargados en XAMPP (admin / usuario)");
        }
    }
}