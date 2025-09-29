package com.gyl.bys.infrastructure.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final UserDetailsService userDetailsService;


    public OAuth2LoginSuccessHandler(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {


        OAuth2User oauth2User = (OAuth2User) auth.getPrincipal();
        String email = oauth2User.getAttribute("email");


        UserDetails userDetails = userDetailsService.loadUserByUsername(email);


        String token = jwtUtils.generateToken(userDetails);
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:4200/login-success")
                .queryParam("token", token).build().toUriString();

        getRedirectStrategy().sendRedirect(req, res, targetUrl);
    }
}