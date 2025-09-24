package com.gyl.bys.domain.models;

import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDate;

public class Candidato extends DomainEntity {


    private String nombre;
    private String apellido;
    private int cuil;
    private int dni;
    private String email;
    private String linkedinLink;
    private String observaciones;
    private int telefono;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_contacto;


















    /*Getter y Setters*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío.");
        }
        this.apellido = apellido;
    }

    public int getCuil() {
        return cuil;
    }

    public void setCuil(int cuil) {
        if (cuil <= 0) {
            throw new IllegalArgumentException("El CUIL debe ser un número positivo.");
        }
        this.cuil = cuil;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("El DNI debe ser un número positivo.");
        }
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email debe ser válido.");
        }
        this.email = email;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {
        if (telefono <= 0) {
            throw new IllegalArgumentException("El teléfono debe ser un número positivo.");
        }
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public LocalDate getFecha_contacto() {
        return fecha_contacto;
    }

    public void setFecha_contacto(LocalDate fecha_contacto) {
        this.fecha_contacto = fecha_contacto;
    }


    /*Builder*/

    private Candidato(Builder builder) {
        super(builder.id); // id obligatorio en DomainEntity
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.cuil = builder.cuil;
        this.dni = builder.dni;
        this.email = builder.email;
        this.linkedinLink = builder.linkedinLink;
        this.observaciones = builder.observaciones;
        this.telefono = builder.telefono;
        this.fecha_nacimiento = builder.fecha_nacimiento;
        this.fecha_contacto = builder.fecha_contacto;
    }

    public static class Builder extends BaseBuilder<Candidato, Builder> {

        private final Long id; // obligatorio
        private String nombre;
        private String apellido;
        private int cuil;
        private int dni;
        private String email;
        private String linkedinLink;
        private String observaciones;
        private int telefono;
        private LocalDate fecha_nacimiento;
        private LocalDate fecha_contacto;

        public Builder(Long id) {
            this.id = id; // validación de DomainEntity
        }

        public Builder nombre(String nombre) { this.nombre = nombre; return self(); }
        public Builder apellido(String apellido) { this.apellido = apellido; return self(); }
        public Builder cuil(int cuil) { this.cuil = cuil; return self(); }
        public Builder dni(int dni) { this.dni = dni; return self(); }
        public Builder email(String email) { this.email = email; return self(); }
        public Builder linkedinLink(String linkedinLink) { this.linkedinLink = linkedinLink; return self(); }
        public Builder observaciones(String observaciones) { this.observaciones = observaciones; return self(); }
        public Builder telefono(int telefono) { this.telefono = telefono; return self(); }
        public Builder fechaNacimiento(LocalDate fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; return self(); }
        public Builder fechaContacto(LocalDate fecha_contacto) { this.fecha_contacto = fecha_contacto; return self(); }

        @Override
        protected Candidato buildEntity() {
            return new Candidato(this);
        }
    }


}
