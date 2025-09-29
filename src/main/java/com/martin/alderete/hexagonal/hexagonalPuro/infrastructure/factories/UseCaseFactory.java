package com.gyl.bys.infrastructure.factories;

import com.gyl.bys.application.usuario.autenticacion.usecase.ProcesarAutenticacionImpl;
import com.gyl.bys.application.usuario.autenticacion.usecase.port.in.ProcesarAutenticacion;
import com.gyl.bys.domain.ports.out.AuthenticationModelPort;
import com.gyl.bys.domain.ports.out.NotificacionAdminModelPort;
import com.gyl.bys.domain.ports.out.PasswordEncoderModelPort;
import com.gyl.bys.domain.ports.out.UsuarioModelPort;
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