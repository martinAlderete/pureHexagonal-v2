package com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase.port.in.ProcesarAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.dto.RegistroUsuarioRequest;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.shared.ResultadoAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.VO.EstadoUsuario;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.exception.DominioNoPermitidoException;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.AuthenticationModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.NotificacionAdminModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.PasswordEncoderModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.UsuarioModelPort;
import java.util.Optional;

public class ProcesarAutenticacionImpl implements ProcesarAutenticacion {

    private final UsuarioModelPort usuarioModelPort;
    private final PasswordEncoderModelPort passwordEncoderModelPort;
    private final NotificacionAdminModelPort notificacionAdminModelPort;
    private final AuthenticationModelPort authenticationModelPort;

    public ProcesarAutenticacionImpl(UsuarioModelPort ump, PasswordEncoderModelPort pemp, NotificacionAdminModelPort namp, AuthenticationModelPort amp) {
        this.usuarioModelPort = ump;
        this.passwordEncoderModelPort = pemp;
        this.notificacionAdminModelPort = namp;
        this.authenticationModelPort = amp;
    }

    @Override
    public ResultadoAutenticacion ejecutar(RegistroUsuarioRequest request) {
        Optional<Usuario> usuarioOpt = usuarioModelPort.findByEmail(request.email());

        if (usuarioOpt.isPresent()) {
            return manejarUsuarioExistente(request, usuarioOpt.get());
        }

        return manejarUsuarioNuevo(request);
    }

    private ResultadoAutenticacion manejarUsuarioExistente(RegistroUsuarioRequest request, Usuario usuario) {
        // Flujo OAuth2 (sin contraseña)
        if (request.password() == null) {
            if (usuario.getEstado() == EstadoUsuario.PENDIENTE) {
                return new ResultadoAutenticacion.UsuarioPendiente(usuario, "Usuario ya registrado, pendiente de aprobación.");
            }
            return new ResultadoAutenticacion.Exitoso(usuario);
        }

        // Flujo Credenciales (con contraseña)
        if (usuario.getEstado() != EstadoUsuario.ACTIVO) {
            return new ResultadoAutenticacion.UsuarioPendiente(usuario, "Su usuario no está activo o está pendiente de aprobación.");
        }
        if (authenticationModelPort.matches(request.password(), usuario.getPassword())) {
            return new ResultadoAutenticacion.Exitoso(usuario);
        } else {
            return new ResultadoAutenticacion.CredencialesInvalidas("Credenciales incorrectas.");
        }
    }

    private ResultadoAutenticacion manejarUsuarioNuevo(RegistroUsuarioRequest request) {
        try {
            String hashedPassword = request.password() != null ? passwordEncoderModelPort.encode(request.password()) : null;
            Usuario nuevoUsuario = Usuario.registrarNuevoUsuario(request.email(), hashedPassword, request.googleId());
            Usuario usuarioGuardado = usuarioModelPort.save(nuevoUsuario);
            notificacionAdminModelPort.notificarNuevoUsuarioPendiente(usuarioGuardado);
            return new ResultadoAutenticacion.UsuarioPendiente(usuarioGuardado, "Su solicitud ha sido enviada para aprobación.");
        } catch (DominioNoPermitidoException e) {
            return new ResultadoAutenticacion.DominioNoPermitido(e.getMessage());
        }
    }
}