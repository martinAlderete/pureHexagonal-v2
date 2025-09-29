package com.martin.alderete.hexagonal.hexagonalPuro.domain.VO;

public record Industria(String nombre) {
    public Industria {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la industria no puede estar vacío.");
        }
    }
}
