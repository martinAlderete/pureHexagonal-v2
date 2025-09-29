package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.mappers;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RolMapper.class, ClienteMapper.class})
public interface UsuarioMapper {


    @Mapping(target = "isAccountNonExpired", source = "accountNonExpired")
    @Mapping(target = "isAccountNonLocked", source = "accountNonLocked")
    @Mapping(target = "isCredentialsNonExpired", source = "credentialsNonExpired")
    UsuarioEntity toEntity(Usuario usuario);



    Usuario toDomain(UsuarioEntity usuarioEntity);
}