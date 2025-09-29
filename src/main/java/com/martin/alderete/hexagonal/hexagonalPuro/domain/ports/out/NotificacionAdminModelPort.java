package com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;

public interface NotificacionAdminModelPort {

    void notificarNuevoUsuarioPendiente(Usuario usuario);
}
