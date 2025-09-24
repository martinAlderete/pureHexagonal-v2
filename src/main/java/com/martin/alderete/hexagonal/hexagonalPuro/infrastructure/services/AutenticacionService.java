package com.gyl.bys.infrastructure.services;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;
import com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in.RegistroUsuario;
import com.gyl.bys.domain.exception.UsuarioPendienteDeAprobacionException;
import com.gyl.bys.infrastructure.controllers.dtos.AuthRequest;
import com.gyl.bys.infrastructure.repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutenticacionService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RegistroUsuario registroUsuario;

    public AutenticacionService(AuthenticationManager am, UsuarioRepository ur, RegistroUsuario ru) {
        this.authenticationManager = am;
        this.usuarioRepository = ur;
        this.registroUsuario = ru;
    }

    // --- ESTE MÉTODO YA NO ES TRANSACCIONAL ---
    public void registrarOProcesarUsuario(AuthRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            // Flujo para usuario existente (no necesita transacción aquí)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } else {
            // Flujo para usuario nuevo
            crearUsuarioPendiente(request);
            // Después de que el usuario se ha creado y guardado, lanzamos la excepción
            throw new UsuarioPendienteDeAprobacionException("Su solicitud ha sido enviada y está pendiente de aprobación.");
        }
    }

    // --- CREAMOS UN NUEVO MÉTODO QUE SÍ ES TRANSACCIONAL ---
    @Transactional
    public void crearUsuarioPendiente(AuthRequest request) {
        try {
            var registroRequest = new RegistroUsuarioRequest(
                    request.email(), request.password(), null);

            // Llamamos al UseCase. Ahora, la excepción que lanza será atrapada aquí.
            registroUsuario.registrarOProcesarUsuario(registroRequest);

        } catch (UsuarioPendienteDeAprobacionException e) {
            // Atrapamos la excepción del UseCase para que NO cause un rollback.
            // La transacción de este método terminará con éxito, guardando al usuario.
            // El método de arriba se encargará de lanzar la excepción de nuevo al controller.
        }
    }
}