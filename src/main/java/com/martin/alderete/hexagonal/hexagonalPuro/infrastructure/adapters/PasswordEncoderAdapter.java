package com.gyl.bys.infrastructure.adapters;

import com.gyl.bys.domain.ports.out.PasswordEncoderModelPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class PasswordEncoderAdapter implements PasswordEncoderModelPort {
    private final PasswordEncoder passwordEncoder;
    public PasswordEncoderAdapter(PasswordEncoder p) {
        this.passwordEncoder = p;
    }
    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}