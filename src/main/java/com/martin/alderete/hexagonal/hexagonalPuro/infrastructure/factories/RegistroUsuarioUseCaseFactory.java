package com.gyl.bys.infrastructure.factories;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.RegistroUsuarioImpl;
import com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in.RegistroUsuario;
import com.gyl.bys.domain.ports.out.NotificacionAdminModelPort;
import com.gyl.bys.domain.ports.out.PasswordEncoderModelPort;
import com.gyl.bys.domain.ports.out.UsuarioModelPort;
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