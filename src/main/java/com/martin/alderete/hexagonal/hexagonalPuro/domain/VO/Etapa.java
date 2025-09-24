package com.gyl.bys.domain.VO;

public record Etapa(String nombre) {
    public Etapa {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la Etapa no puede estar vacío.");
        }
    }
}
