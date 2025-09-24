package com.gyl.bys.domain.VO;

public record Habilidad(String nombre) {
    public Habilidad {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la Habilidad no puede estar vacío.");
        }
    }
}