package com.gyl.bys.domain.VO;

public record Seniority(String nombre) {
    public Seniority {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del Seniority no puede estar vacío.");
        }
    }
}