package cl.sonidovivo.ms_usuarios.repository;

import cl.sonidovivo.ms_usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
}