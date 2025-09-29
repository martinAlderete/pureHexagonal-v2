package com.martin.alderete.hexagonal.hexagonalPuro.domain.models;

import com.martin.alderete.hexagonal.hexagonalPuro.domain.models.abs.DomainEntity;

public class Cliente extends DomainEntity {

    private String nombreFantasia;

    public Cliente(Long id, String nombreFantasia) {
        super(id);
        if (nombreFantasia == null || nombreFantasia.isBlank()) {
            throw new IllegalArgumentException("El nombre de fantasía del cliente no puede estar vacío.");
        }
        this.nombreFantasia = nombreFantasia;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }
}
