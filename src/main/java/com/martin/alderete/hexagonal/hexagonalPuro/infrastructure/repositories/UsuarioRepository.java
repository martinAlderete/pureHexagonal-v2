package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.repositories;

import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);
}
