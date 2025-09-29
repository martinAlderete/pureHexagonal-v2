package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.factories;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.registrarOProcesarUsuario.usecase.RegistroUsuarioImpl;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.registrarOProcesarUsuario.usecase.port.in.RegistroUsuario;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.NotificacionAdminModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.PasswordEncoderModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.UsuarioModelPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistroUsuarioUseCaseFactory {

    @Bean
    public RegistroUsuario registroUsuario(UsuarioModelPort usuarioModelPort, NotificacionAdminModelPort notificacionAdminModelPort, PasswordEncoderModelPort passwordEncoderModelPort
    ) {

        return new RegistroUsuarioImpl(usuarioModelPort, passwordEncoderModelPort, notificacionAdminModelPort);
    }
}