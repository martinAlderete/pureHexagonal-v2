package com.gyl.bys.infrastructure.adapters;

import com.gyl.bys.domain.ports.out.AuthenticationModelPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter implements AuthenticationModelPort {
    private final PasswordEncoder passwordEncoder;

    public AuthenticationAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}