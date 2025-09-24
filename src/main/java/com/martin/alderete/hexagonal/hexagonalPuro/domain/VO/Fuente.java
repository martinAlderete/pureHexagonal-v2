package com.gyl.bys.domain.VO;

public record Fuente(String nombre) {
    public Fuente {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la fuente no puede estar vacío.");
        }
    }
}
