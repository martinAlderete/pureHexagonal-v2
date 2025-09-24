package com.gyl.bys.infrastructure.config.security;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;
import com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in.RegistroUsuario;
import com.gyl.bys.domain.exception.*;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
import com.gyl.bys.infrastructure.repositories.UsuarioRepository;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final RegistroUsuario registroUsuario;
    private final UsuarioRepository usuarioRepository;

    public CustomOAuth2UserService(RegistroUsuario uc, UsuarioRepository ur) {
        this.registroUsuario = uc;
        this.usuarioRepository = ur;
    }

    @Override
    @Transactional // <-- Ahora Spring sí entiende esta anotación
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        try {
            RegistroUsuarioRequest registroUsuarioRequest = new RegistroUsuarioRequest(
                    oauth2User.getAttribute("email"), null, oauth2User.getName());
            registroUsuario.registrarOProcesarUsuario(registroUsuarioRequest);
        } catch (UsuarioPendienteDeAprobacionException | DominioNoPermitidoException e) {
            throw new OAuth2AuthenticationException(e.getMessage());
        }
        UsuarioEntity user = usuarioRepository.findByEmail(oauth2User.getAttribute("email"))
                .orElseThrow(() -> new OAuth2AuthenticationException("Error al procesar usuario."));
        user.setAttributes(oauth2User.getAttributes());
        return user;
    }
}