package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.controllers;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.shared.ResultadoAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.config.security.JwtUtils;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.controllers.dtos.AuthRequest;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.controllers.dtos.AuthResponse;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.UsuarioEntity;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.mappers.UsuarioMapper;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.services.AutenticacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AutenticacionService autenticacionService;
    private final JwtUtils jwtUtils;
    private final UsuarioMapper usuarioMapper;

    public AuthenticationController(AutenticacionService as, JwtUtils ju, UsuarioMapper um) {
        this.autenticacionService = as;
        this.jwtUtils = ju;
        this.usuarioMapper = um;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        ResultadoAutenticacion resultado = autenticacionService.ejecutarLoginConCredenciales(request);

        return switch (resultado) {
            case ResultadoAutenticacion.Exitoso exitoso -> {
                UsuarioEntity userDetails = usuarioMapper.toEntity(exitoso.usuario());
                String token = jwtUtils.generateToken(userDetails);
                yield ResponseEntity.ok(new AuthResponse(token));
            }
            case ResultadoAutenticacion.UsuarioPendiente pendiente ->
                    ResponseEntity.status(HttpStatus.FORBIDDEN).body(pendiente.mensaje());
            case ResultadoAutenticacion.DominioNoPermitido dominio ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dominio.mensaje());
            case ResultadoAutenticacion.CredencialesInvalidas invalidas ->
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(invalidas.mensaje());
        };
    }
}