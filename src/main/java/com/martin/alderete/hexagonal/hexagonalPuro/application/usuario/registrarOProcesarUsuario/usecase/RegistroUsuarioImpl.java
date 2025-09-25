package com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;
import com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in.RegistroUsuario;
import com.gyl.bys.domain.exception.UsuarioPendienteDeAprobacionException;
import com.gyl.bys.domain.models.Usuario;
import com.gyl.bys.domain.ports.out.NotificacionAdminModelPort;
import com.gyl.bys.domain.ports.out.PasswordEncoderModelPort;
import com.gyl.bys.domain.ports.out.UsuarioModelPort;

import java.util.Optional;

public class RegistroUsuarioImpl implements RegistroUsuario {


    private final UsuarioModelPort usuarioModelPort;
    private final PasswordEncoderModelPort passwordEncoderModelPort;
    private final NotificacionAdminModelPort notificacionAdminModelPort;



    public RegistroUsuarioImpl(UsuarioModelPort usuarioModelPort, PasswordEncoderModelPort passwordEncoderModelPort, NotificacionAdminModelPort notificacionAdminModelPort) {
        this.usuarioModelPort = usuarioModelPort;
        this.passwordEncoderModelPort = passwordEncoderModelPort;
        this.notificacionAdminModelPort = notificacionAdminModelPort;
    }

    @Override
    public Optional<Usuario> registrarOProcesarUsuario(RegistroUsuarioRequest request) { // Devuelve Optional<Usuario>
        Optional<Usuario> usuarioExistente = usuarioModelPort.findByEmail(request.email());

        // Si el usuario ya existe, lo devolvemos directamente.
        if(usuarioExistente.isPresent()) {
            return usuarioExistente;
        }

        // Si es un usuario nuevo, lo creamos y guardamos.
        String hashPassword = (request.password() != null) ? passwordEncoderModelPort.encode(request.password()) : null;
        Usuario usuarioNuevo = Usuario.registrarNuevoUsuario(request.email(), hashPassword, request.googleId());

        Usuario usuarioGuardado = usuarioModelPort.save(usuarioNuevo);
        notificacionAdminModelPort.notificarNuevoUsuarioPendiente(usuarioGuardado);

        // Devolvemos el usuario recién creado. NO lanzamos excepción.
        return Optional.of(usuarioGuardado);
    }

}
