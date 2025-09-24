package com.gyl.bys.domain.ports.out;

import com.gyl.bys.domain.models.Usuario;

import java.util.Optional;

public interface UsuarioModelPort {

    Optional<Usuario> findByEmail(String email);

    Usuario save(Usuario usuario);


}
