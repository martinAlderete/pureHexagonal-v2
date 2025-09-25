package com.gyl.bys.infrastructure.mappers;

import com.gyl.bys.domain.models.Usuario;
import com.gyl.bys.infrastructure.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RolMapper.class, ClienteMapper.class})
public interface UsuarioMapper {

    // --- Mapeo de Dominio a Entidad ---
    // Le indicamos explícitamente el source y el target para los booleanos
    // para resolver el conflicto entre MapStruct y Lombok Builder.
    @Mapping(target = "isAccountNonExpired", source = "accountNonExpired")
    @Mapping(target = "isAccountNonLocked", source = "accountNonLocked")
    @Mapping(target = "isCredentialsNonExpired", source = "credentialsNonExpired")
    UsuarioEntity toEntity(Usuario usuario);


    // --- Mapeo de Entidad a Dominio ---
    // MapStruct no puede usar setters (porque no existen). Automáticamente
    // detectará el constructor público y mapeará los campos por nombre.
    // No necesitamos anotaciones aquí porque los nombres de los campos/getters
    // en la Entidad coinciden con los parámetros del constructor del Dominio.
    Usuario toDomain(UsuarioEntity usuarioEntity);
}