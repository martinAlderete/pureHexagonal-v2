package com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.registrarOProcesarUsuario.usecase.port.in;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;

import java.util.Optional;

public interface RegistroUsuario {

    Optional<Usuario> registrarOProcesarUsuario(RegistroUsuarioRequest request);
}
