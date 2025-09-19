package com.gyl.bys.infrastructure.controllers;



import com.gyl.bys.infrastructure.controllers.dtos.AuthRequest;
import com.gyl.bys.infrastructure.controllers.dtos.AuthResponse;
import com.gyl.bys.infrastructure.config.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}

