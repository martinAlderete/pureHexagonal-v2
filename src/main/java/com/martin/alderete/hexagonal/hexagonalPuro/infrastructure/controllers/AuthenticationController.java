package com.gyl.bys.infrastructure.controllers;

import com.gyl.bys.domain.exception.DominioNoPermitidoException;
import com.gyl.bys.domain.exception.UsuarioPendienteDeAprobacionException;
import com.gyl.bys.infrastructure.config.security.JwtUtils;
import com.gyl.bys.infrastructure.controllers.dtos.AuthRequest;
import com.gyl.bys.infrastructure.controllers.dtos.AuthResponse;
import com.gyl.bys.infrastructure.services.AutenticacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    // --- CAMBIO 1: Inyectamos el nuevo Service Wrapper y las utilidades para el JWT ---
    private final AutenticacionService autenticacionService;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(
            AutenticacionService autenticacionService,
            JwtUtils jwtUtils,
            UserDetailsService userDetailsService
    ) {
        this.autenticacionService = autenticacionService;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            // --- CAMBIO 2: Se llama al Service Wrapper que contiene la lógica ---
            autenticacionService.registrarOProcesarUsuario(request);

            // Si la línea anterior no lanza excepción, el usuario es válido.
            // Procedemos a generar y devolver el token JWT.
            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
            final String token = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));

        }
        // --- CAMBIO 3: Manejo de todas las posibles excepciones ---
        catch (AuthenticationException e) {
            // Captura errores de Spring Security (mal password, cuenta inactiva/bloqueada, etc.)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: credenciales incorrectas o cuenta deshabilitada.");
        } catch (UsuarioPendienteDeAprobacionException e) {
            // Captura nuestro caso de negocio de "usuario pendiente"
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (DominioNoPermitidoException e) {
            // Captura nuestro caso de negocio de "dominio no permitido"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}