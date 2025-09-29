package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.services;

import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.usecase.port.in.ProcesarAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.autenticacion.dto.RegistroUsuarioRequest;
import com.martin.alderete.hexagonal.hexagonalPuro.application.usuario.shared.ResultadoAutenticacion;
import com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.controllers.dtos.AuthRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutenticacionService {

    private final ProcesarAutenticacion procesarAutenticacion;

    public AutenticacionService(ProcesarAutenticacion procesarAutenticacion) {
        this.procesarAutenticacion = procesarAutenticacion;
    }

    @Transactional
    public ResultadoAutenticacion ejecutarLoginConCredenciales(AuthRequest request) {
        var useCaseRequest = new RegistroUsuarioRequest(request.email(), request.password(), null);
        return procesarAutenticacion.ejecutar(useCaseRequest);
    }
}