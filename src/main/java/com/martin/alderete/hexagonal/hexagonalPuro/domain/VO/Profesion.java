package com.gyl.bys.domain.VO;

public record Profesion(String nombre) {

    public Profesion {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la Profesión no puede estar vacío.");
        }
    }
}
