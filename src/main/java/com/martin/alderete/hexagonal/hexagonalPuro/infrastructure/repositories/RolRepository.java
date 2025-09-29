package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.repositories;

import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity,Long> {
}
