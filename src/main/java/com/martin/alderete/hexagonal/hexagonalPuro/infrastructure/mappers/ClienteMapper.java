package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.mappers;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Cliente;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.ClienteEntity;
import org.mapstruct.*;
@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(source = "nombreFantasia", target = "nombre")
    ClienteEntity toEntity(Cliente cliente);
    @Mapping(source = "nombre", target = "nombreFantasia")
    Cliente toDomain(ClienteEntity clienteEntity);
}