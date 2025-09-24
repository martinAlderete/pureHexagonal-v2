package com.gyl.bys.infrastructure.adapters;

import com.gyl.bys.domain.models.Usuario;
import com.gyl.bys.domain.ports.out.NotificacionAdminModelPort;
import org.slf4j.*;
import org.springframework.stereotype.Component;
@Component
public class LogNotificacionAdminAdapter implements NotificacionAdminModelPort {
    private static final Logger log = LoggerFactory.getLogger(LogNotificacionAdminAdapter.class);
    @Override public void notificarNuevoUsuarioPendiente(Usuario u) {
        log.warn("ACCIÓN REQUERIDA: Usuario con email '{}' está pendiente de aprobación.", u.getEmail());
    }
}