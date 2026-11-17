package cl.sonidovivo.ms_usuarios.controller;

import cl.sonidovivo.ms_usuarios.dto.LoginRequest;
import cl.sonidovivo.ms_usuarios.dto.RegistroRequest;
import cl.sonidovivo.ms_usuarios.model.Usuario;
import cl.sonidovivo.ms_usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Usuarios y Autenticación", description = "Endpoints para login, registro y gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/auth/login")
    @Operation(summary = "Iniciar sesión con usuario y contraseña")
    public ResponseEntity<Usuario> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @PostMapping("/auth/registro")
    @Operation(summary = "Registrar una nueva cuenta de cliente")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.registrar(request));
    }

    @GetMapping("/usuarios")
    @Operation(summary = "Listar todos los usuarios (Panel Admin)")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PatchMapping("/usuarios/{id}/rol")
    @Operation(summary = "Cambiar rol entre admin y cliente")
    public ResponseEntity<Usuario> cambiarRol(@PathVariable Long id, @RequestParam String rol) {
        return ResponseEntity.ok(usuarioService.cambiarRol(id, rol));
    }
}