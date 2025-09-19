package com.gyl.bys.infrastructure.config.security;



import com.gyl.bys.infrastructure.controllers.dtos.AuthRequest;
import com.gyl.bys.infrastructure.controllers.dtos.AuthResponse;
import com.gyl.bys.infrastructure.repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthenticationService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

       final UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());


        String token = jwtUtils.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
