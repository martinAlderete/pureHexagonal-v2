package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.factories;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase.ProcesarAutenticacionImpl;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase.port.in.ProcesarAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.AuthenticationModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.NotificacionAdminModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.PasswordEncoderModelPort;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.UsuarioModelPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseFactory {

    @Bean
    public ProcesarAutenticacion procesarAutenticacion(
            UsuarioModelPort usuarioModelPort,
            PasswordEncoderModelPort passwordEncoderModelPort,
            NotificacionAdminModelPort notificacionAdminModelPort,
            AuthenticationModelPort authenticationModelPort
    ) {
        return new ProcesarAutenticacionImpl(
                usuarioModelPort,
                passwordEncoderModelPort,
                notificacionAdminModelPort,
                authenticationModelPort
        );
    }
}