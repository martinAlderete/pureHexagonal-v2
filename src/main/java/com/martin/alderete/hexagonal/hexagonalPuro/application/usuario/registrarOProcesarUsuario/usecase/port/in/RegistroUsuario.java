package com.gyl.bys.application.usuario.registrarOProcesarUsuario.usecase.port.in;

import com.gyl.bys.application.usuario.registrarOProcesarUsuario.dto.RegistroUsuarioRequest;

public interface RegistroUsuario {

    public void registrarOProcesarUsuario(RegistroUsuarioRequest request);
}
