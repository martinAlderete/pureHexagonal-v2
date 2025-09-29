package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.mappers;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Rol;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.RolEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolEntity toEntity(Rol rol);
    @InheritInverseConfiguration
    Rol toDomain(RolEntity rolEntity);
}