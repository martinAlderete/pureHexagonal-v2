package com.gyl.bys.domain.models;

import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDateTime;

public class Rol extends DomainEntity {

    private String nombre;
    private LocalDateTime fechaCreacion;
    private boolean activo;

    public Rol(Long id, String nombre, LocalDateTime fechaCreacion) {
        super(id);
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    /* ---------------- Métodos de negocio ---------------- */

    public void renombrar(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede ser nulo o vacío.");
        }
        this.nombre = nuevoNombre;
    }

    public void activar() {
        if (this.activo) {
            throw new IllegalStateException("El rol ya está activo.");
        }
        this.activo = true;
    }

    public void desactivar() {
        if (!this.activo) {
            throw new IllegalStateException("El rol ya está inactivo.");
        }
        this.activo = false;
    }

    /* ---------------- Getters ---------------- */

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public boolean isActivo() { return activo;}

    /* ---------------- Creación y reconstitución ---------------- */

    // Creación desde cero (nuevo rol)
    public static Rol create(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del rol es obligatorio.");
        }
        return new Rol(null, nombre, LocalDateTime.now(), true);
    }

    // Reconstitución desde la base de datos
    public static Rol reconstitute(Long id, String nombre, LocalDateTime fechaCreacion, boolean activo) {
        if (id == null) {
            throw new IllegalArgumentException("El id es obligatorio para reconstituir un rol.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio para reconstituir un rol.");
        }
        if (fechaCreacion == null) {
            throw new IllegalArgumentException("La fecha de creación es obligatoria para reconstituir un rol.");
        }
        return new Rol(id, nombre, fechaCreacion, activo);
    }

    /* ---------------- Constructor privado ---------------- */

    private Rol(Long id, String nombre, LocalDateTime fechaCreacion, boolean activo) {
        super(id);
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }
}
