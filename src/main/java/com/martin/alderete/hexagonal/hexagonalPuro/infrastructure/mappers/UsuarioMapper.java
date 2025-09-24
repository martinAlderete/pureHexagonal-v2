package com.gyl.bys.infrastructure.mappers;

import com.gyl.bys.domain.models.Usuario;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {RolMapper.class, ClienteMapper.class})
public interface UsuarioMapper {
    UsuarioEntity toEntity(Usuario usuario);
    Usuario toDomain(UsuarioEntity usuarioEntity);
}