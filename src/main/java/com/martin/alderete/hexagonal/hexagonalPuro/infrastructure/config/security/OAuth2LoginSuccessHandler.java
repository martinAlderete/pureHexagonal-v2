package com.gyl.bys.infrastructure.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // <-- Importante
import org.springframework.security.oauth2.core.user.OAuth2User; // <-- Importante
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;
    // --- CAMBIO 1: Inyectamos UserDetailsService para poder buscar a nuestro usuario ---
    private final UserDetailsService userDetailsService;

    public OAuth2LoginSuccessHandler(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        // Obtenemos el principal como el tipo que realmente es: OAuth2User
        OAuth2User oauth2User = (OAuth2User) auth.getPrincipal();

        // Extraemos el email, que es nuestro identificador único
        String email = oauth2User.getAttribute("email");

        // --- CAMBIO 2: Usamos el email para cargar nuestro propio UserDetails (UsuarioEntity) ---
        // Esto nos asegura que trabajamos con nuestra entidad y no con la de Spring
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // A partir de aquí, el resto del código funciona igual
        String token = jwtUtils.generateToken(userDetails);
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:4200/login-success")
                .queryParam("token", token).build().toUriString();

        getRedirectStrategy().sendRedirect(req, res, targetUrl);
    }
}