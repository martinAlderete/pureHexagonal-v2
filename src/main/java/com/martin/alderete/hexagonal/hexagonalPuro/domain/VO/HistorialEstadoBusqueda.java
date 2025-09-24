package com.gyl.bys.domain.VO;

import java.time.LocalDateTime;

public record HistorialEstadoBusqueda(Estado estado, LocalDateTime fecha) {
    public HistorialEstadoBusqueda {
        if (estado == null) {
            throw new IllegalArgumentException("El Estado en una entrada de historial no puede ser nulo.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha en una entrada de historial no puede ser nula.");
        }
    }
}