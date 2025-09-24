package com.gyl.bys.domain.models;

import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDateTime;

public class Postulacion extends DomainEntity {

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaPostulacion;
    private Usuario usuario;
    private Candidato candidato;
    private Busqueda busqueda;



    public Postulacion(Long id, LocalDateTime fechaCreacion, LocalDateTime fechaPostulacion, Usuario usuario, Candidato candidato, Busqueda busqueda) {
        super(id);
        this.fechaCreacion = fechaCreacion;
        this.fechaPostulacion = fechaPostulacion;
        this.usuario = usuario;
        this.candidato = candidato;
        this.busqueda = busqueda;
    }












    /*Getter y Setters*/

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        if (fechaCreacion == null) {
            throw new IllegalArgumentException("Una Postulación debe tener una fecha de creación");
        }
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDateTime fechaPostulacion) {
        if (fechaPostulacion == null) {
            throw new IllegalArgumentException("Una Postulación debe tener una fecha de postulación");
        }
        this.fechaPostulacion = fechaPostulacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Una Postulación debe estar asociada a un usuario");
        }
        this.usuario = usuario;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Una Postulación debe estar asociada a un candidato");
        }
        this.candidato = candidato;
    }

    public Busqueda getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Busqueda busqueda) {
        if (busqueda == null) {
            throw new IllegalArgumentException("Una Postulación debe estar asociada a una busqueda");
        }
        this.busqueda = busqueda;
    }


    /*Builder*/

    private Postulacion(Builder builder) {
        super(builder.id); // id obligatorio en DomainEntity
        this.fechaCreacion = builder.fechaCreacion;
        this.fechaPostulacion = builder.fechaPostulacion;
        this.usuario = builder.usuario;
        this.candidato = builder.candidato;
        this.busqueda = builder.busqueda;
    }

    public static class Builder extends BaseBuilder<Postulacion, Builder> {

        private final Long id; // obligatorio
        private LocalDateTime fechaCreacion;
        private LocalDateTime fechaPostulacion;
        private Usuario usuario;
        private Candidato candidato;
        private Busqueda busqueda;

        public Builder(Long id) {
            this.id = id; // validación de DomainEntity
        }

        public Builder fechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; return self(); }
        public Builder fechaPostulacion(LocalDateTime fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; return self(); }
        public Builder usuario(Usuario usuario) { this.usuario = usuario; return self(); }
        public Builder candidato(Candidato candidato) { this.candidato = candidato; return self(); }
        public Builder busqueda(Busqueda busqueda) { this.busqueda = busqueda; return self(); }

        @Override
        protected Postulacion buildEntity() {
            return new Postulacion(this);
        }
    }

}
