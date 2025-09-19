package com.gyl.bys.infrastructure.config;

import com.gyl.bys.infrastructure.entities.RolEntity;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
import com.gyl.bys.infrastructure.repositories.RolRepository;
import com.gyl.bys.infrastructure.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.count() == 0) {
            rolRepository.save(new RolEntity("ADMIN"));
            rolRepository.save(new RolEntity("RECLUTADOR"));
            rolRepository.save(new RolEntity("COMERCIAL"));
        }

        if (!usuarioRepository.findByEmail("admin@bys.com").isPresent()) {
            RolEntity adminRol = rolRepository.findAll().stream()
                    .filter(rol -> rol.getNombre().equals("ADMIN"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Error: Rol ADMIN no encontrado."));

            UsuarioEntity adminUser = new UsuarioEntity();
            adminUser.setEmail("admin@bys.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setFechaAlta(LocalDateTime.now());
            adminUser.setRoles(Set.of(adminRol));
            adminUser.setEnabled(true);
            adminUser.setAccountNonExpired(true);
            adminUser.setAccountNonLocked(true);
            adminUser.setCredentialsNonExpired(true);

            usuarioRepository.save(adminUser);
        }
    }
}