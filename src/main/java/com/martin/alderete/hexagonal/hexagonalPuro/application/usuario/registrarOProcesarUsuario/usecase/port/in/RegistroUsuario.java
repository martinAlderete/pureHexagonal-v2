package com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;
import com.gyl.bys.domain.models.Usuario;

import java.util.Optional;

public interface RegistroUsuario {

    Optional<Usuario> registrarOProcesarUsuario(RegistroUsuarioRequest request);
}
