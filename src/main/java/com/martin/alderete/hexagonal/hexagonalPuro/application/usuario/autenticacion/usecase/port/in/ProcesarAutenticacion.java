package com.gyl.bys.application.usuario.autenticacion.usecase.port.in;

import com.gyl.bys.application.usuario.autenticacion.dto.RegistroUsuarioRequest;
import com.gyl.bys.application.usuario.shared.ResultadoAutenticacion;

public interface ProcesarAutenticacion {
    ResultadoAutenticacion ejecutar(RegistroUsuarioRequest request);
}