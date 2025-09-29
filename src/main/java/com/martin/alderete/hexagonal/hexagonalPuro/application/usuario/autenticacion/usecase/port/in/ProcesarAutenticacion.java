package com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase.port.in;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.dto.RegistroUsuarioRequest;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.shared.ResultadoAutenticacion;

public interface ProcesarAutenticacion {
    ResultadoAutenticacion ejecutar(RegistroUsuarioRequest request);
}