package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.adapters;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.Usuario;
import com.martin.alderete.hexagonal.hexagonalPuro.domain.ports.out.NotificacionAdminModelPort;
import org.slf4j.*;
import org.springframework.stereotype.Component;
@Component
public class LogNotificacionAdminAdapter implements NotificacionAdminModelPort {
    private static final Logger log = LoggerFactory.getLogger(LogNotificacionAdminAdapter.class);
    @Override public void notificarNuevoUsuarioPendiente(Usuario u) {
        log.warn("ACCIÓN REQUERIDA: Usuario con email '{}' está pendiente de aprobación.", u.getEmail());
    }
}