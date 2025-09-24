package com.gyl.bys.domain.models;

import com.gyl.bys.domain.VO.Etapa;
import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDateTime;

public class HistorialEtapaPostulacion extends DomainEntity {

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private Etapa etapa;
    private Postulacion postulacion;


    public HistorialEtapaPostulacion(Long id, LocalDateTime fechaInicio, LocalDateTime fechaFinalizacion, Etapa etapa, Postulacion postulacion) {
        super(id);
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.etapa = etapa;
        this.postulacion = postulacion;
    }













    /*Getter y Setters*/

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        if (fechaInicio == null) {
            throw  new IllegalArgumentException("La fecha de inicio de la etapa no puede ser nula");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        if (etapa == null) {
            throw  new IllegalArgumentException("Una etapa debe estar asociada a una postulación");
        }
        this.etapa = etapa;
    }

    public Postulacion getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(Postulacion postulacion) {
        if (postulacion == null) {
            throw  new IllegalArgumentException("Una postulación debe estar asociada a una etapa");
        }
        this.postulacion = postulacion;
    }



    /*Builder*/

    private HistorialEtapaPostulacion(Builder builder) {
        super(builder.id); // id obligatorio en DomainEntity
        this.fechaInicio = builder.fechaInicio;
        this.fechaFinalizacion = builder.fechaFinalizacion;
        this.etapa = builder.etapa;
        this.postulacion = builder.postulacion;
    }

    public static class Builder extends BaseBuilder<HistorialEtapaPostulacion, Builder> {

        private final Long id; // obligatorio
        private LocalDateTime fechaInicio;
        private LocalDateTime fechaFinalizacion;
        private Etapa etapa;
        private Postulacion postulacion;

        public Builder(Long id) {
            this.id = id; // validación de DomainEntity
        }

        public Builder fechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; return self(); }
        public Builder fechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; return self(); }
        public Builder etapa(Etapa etapa) { this.etapa = etapa; return self(); }
        public Builder postulacion(Postulacion postulacion) { this.postulacion = postulacion; return self(); }

        @Override
        protected HistorialEtapaPostulacion buildEntity() {
            return new HistorialEtapaPostulacion(this);
        }
    }


}
