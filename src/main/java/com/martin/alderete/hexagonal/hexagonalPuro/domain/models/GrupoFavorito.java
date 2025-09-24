package com.gyl.bys.domain.models;

import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;

import java.util.ArrayList;
import java.util.List;

public class GrupoFavorito  extends DomainEntity {

    private String nombre;
    private Long usuario_id;
    private List<Candidato> candidatos;


    public GrupoFavorito(Long id, String nombre, Long usuario_id) {
        super(id);
        this.nombre = nombre;
        this.usuario_id = usuario_id;
    }








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

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        if (usuario_id == null || usuario_id <= 0) {
            throw new IllegalArgumentException("El ID de usuario debe ser un número positivo.");
        }
        this.usuario_id = usuario_id;
    }











    /*Builder*/

    private GrupoFavorito(Builder builder) {
        super(builder.id); // id obligatorio en DomainEntity
        this.nombre = builder.nombre;
        this.usuario_id = builder.usuario_id;
        this.candidatos = builder.safeList(builder.candidatos);
    }

    public static class Builder extends BaseBuilder<GrupoFavorito, Builder> {

        private final Long id; // obligatorio
        private String nombre;
        private Long usuario_id;
        private List<Candidato> candidatos = new ArrayList<>();

        public Builder(Long id) {
            this.id = id; // validación de DomainEntity
        }

        public Builder nombre(String nombre) { this.nombre = nombre; return self(); }
        public Builder usuarioId(Long usuario_id) { this.usuario_id = usuario_id; return self(); }

        // Lista completa
        public Builder candidatos(List<Candidato> candidatos) { this.candidatos = candidatos; return self(); }

        // Agregar individualmente
        public Builder addCandidato(Candidato candidato) { this.candidatos.add(candidato); return self(); }

        @Override
        protected GrupoFavorito buildEntity() {
            return new GrupoFavorito(this);
        }
    }



}
