package com.gyl.bys.domain.VO;

public record Permiso(String nombre) {
    public Permiso{
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre del Permiso no puede estar vacío.");
        }
    }
}
