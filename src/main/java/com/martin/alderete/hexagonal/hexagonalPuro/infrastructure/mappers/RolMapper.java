package com.gyl.bys.infrastructure.mappers;

import com.gyl.bys.domain.models.Rol;
import com.gyl.bys.infrastructure.entities.RolEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolEntity toEntity(Rol rol);
    @InheritInverseConfiguration
    Rol toDomain(RolEntity rolEntity);
}