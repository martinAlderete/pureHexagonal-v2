package com.gyl.bys.infrastructure.config.security;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;
import com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in.RegistroUsuario;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
import com.gyl.bys.infrastructure.repositories.UsuarioRepository;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final RegistroUsuario registroUsuario;
    private final UsuarioRepository usuarioRepository;
    private static final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    public CustomOAuth2UserService(RegistroUsuario uc, UsuarioRepository ur) {
        this.registroUsuario = uc;
        this.usuarioRepository = ur;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("--- INICIO CustomOAuth2UserService.loadUser ---"); // LOG 1
        OAuth2User oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        String googleId = oauth2User.getName(); // O el atributo que corresponda a su ID de Google

        log.info("Usuario de Google recibido. Email: [{}], GoogleId: [{}]", email, googleId); // LOG 2

        try {
            RegistroUsuarioRequest registroRequest = new RegistroUsuarioRequest(email, null, googleId);
            log.info("Llamando al caso de uso 'registrarOProcesarUsuario'..."); // LOG 3
            registroUsuario.registrarOProcesarUsuario(registroRequest);
            log.info("El caso de uso terminó. Procediendo a buscar el usuario en la BDD."); // LOG 4

            UsuarioEntity user = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> {
                        log.error("¡FALLO CRÍTICO! El usuario con email [{}] NO fue encontrado en la BDD después de procesarlo.", email); // LOG 5
                        return new OAuth2AuthenticationException("Error fatal: el usuario debería existir en este punto.");
                    });

            log.info("Éxito: Usuario con email [{}] encontrado en la BDD. ID: {}", user.getEmail(), user.getId()); // LOG 6
            user.setAttributes(oauth2User.getAttributes());
            return user;

        } catch (Exception e) { // Cambiado a Exception para capturar TODO
            log.error("!!! EXCEPCIÓN CAPTURADA en CustomOAuth2UserService: ", e); // LOG 7
            throw new OAuth2AuthenticationException(e.getMessage());
        }
    }
}