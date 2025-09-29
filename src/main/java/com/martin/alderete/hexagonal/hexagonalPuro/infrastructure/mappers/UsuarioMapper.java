package com.gyl.bys.infrastructure.mappers;

import com.gyl.bys.domain.models.Usuario;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
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