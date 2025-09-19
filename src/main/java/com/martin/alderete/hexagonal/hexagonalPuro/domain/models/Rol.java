package com.gyl.bys.domain.models;

import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDateTime;

public class Rol extends DomainEntity {

    String nombre;
    LocalDateTime fechaCreacion;

    public Rol(Long id, String nombre, LocalDateTime fechaCreacion) {
        super(id);
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
