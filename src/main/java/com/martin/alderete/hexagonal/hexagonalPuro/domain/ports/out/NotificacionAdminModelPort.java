package com.gyl.bys.domain.ports.out;

import com.gyl.bys.domain.models.Usuario;

public interface NotificacionAdminModelPort {

    void notificarNuevoUsuarioPendiente(Usuario usuario);
}
