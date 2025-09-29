package com.martin.alderete.hexagonal.hexagonalPuro.domain.VO;

public record Estado(String nombre) {
    public Estado {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del Estado no puede estar vacío.");
        }
    }
}