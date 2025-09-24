package com.gyl.bys.domain.VO;

public record ModalidadContratacion(String nombre) {

    public ModalidadContratacion{
        if(nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre de la modalidad no puede estar vacío.");
    }
}
