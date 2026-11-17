package cl.sonidovivo.ms_usuarios.service;

import cl.sonidovivo.ms_usuarios.dto.LoginRequest;
import cl.sonidovivo.ms_usuarios.dto.RegistroRequest;
import cl.sonidovivo.ms_usuarios.model.Usuario;
import cl.sonidovivo.ms_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registrar(RegistroRequest request) {
        if (usuarioRepository.existsByNombre(request.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario ya está registrado.");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ese correo electrónico ya está en uso.");
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(request.getNombre().trim());
        nuevo.setEmail(request.getEmail().trim());
        nuevo.setPassword(passwordEncoder.encode(request.getPassword()));
        nuevo.setRol("cliente");

        return usuarioRepository.save(nuevo);
    }

    public Usuario login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByNombre(request.getNombre().trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos."));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario o contraseña incorrectos.");
        }

        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario cambiarRol(Long id, String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado."));
        usuario.setRol(nuevoRol.toLowerCase());
        return usuarioRepository.save(usuario);
    }
}