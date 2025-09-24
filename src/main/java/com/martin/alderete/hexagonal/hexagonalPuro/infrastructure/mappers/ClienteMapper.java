package com.gyl.bys.infrastructure.mappers;

import com.gyl.bys.domain.models.Cliente;
import com.gyl.bys.infrastructure.entities.ClienteEntity;
import org.mapstruct.*;
@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(source = "nombreFantasia", target = "nombre")
    ClienteEntity toEntity(Cliente cliente);
    @Mapping(source = "nombre", target = "nombreFantasia")
    Cliente toDomain(ClienteEntity clienteEntity);
}