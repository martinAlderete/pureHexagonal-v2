package com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;

import java.util.Optional;

public interface UsuarioModelPort {

    Optional<Usuario> findByEmail(String email);

    Usuario save(Usuario usuario);


}
