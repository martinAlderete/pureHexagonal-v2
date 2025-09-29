package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.config.security;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase.port.in.ProcesarAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.dto.RegistroUsuarioRequest;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.shared.ResultadoAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.UsuarioEntity;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.mappers.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final ProcesarAutenticacion procesarAutenticacion;
    private final UsuarioMapper usuarioMapper;
    private static final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    public CustomOAuth2UserService(ProcesarAutenticacion procesarAutenticacion, UsuarioMapper usuarioMapper) {
        this.procesarAutenticacion = procesarAutenticacion;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        String googleId = oauth2User.getName();

        var request = new RegistroUsuarioRequest(email, null, googleId);
        ResultadoAutenticacion resultado = procesarAutenticacion.ejecutar(request);

        Usuario usuarioDeDominio = switch(resultado) {
            case ResultadoAutenticacion.Exitoso exitoso -> exitoso.usuario();
            case ResultadoAutenticacion.UsuarioPendiente pendiente -> pendiente.usuario();
            case ResultadoAutenticacion.DominioNoPermitido dominio -> {
                throw new OAuth2AuthenticationException(dominio.mensaje());
            }
            case ResultadoAutenticacion.CredencialesInvalidas cred -> {
                throw new IllegalStateException("Estado inalcanzable 'CredencialesInvalidas' en flujo OAuth2.");
            }
        };

        UsuarioEntity userEntity = usuarioMapper.toEntity(usuarioDeDominio);
        userEntity.setAttributes(oauth2User.getAttributes());
        return userEntity;
    }
}