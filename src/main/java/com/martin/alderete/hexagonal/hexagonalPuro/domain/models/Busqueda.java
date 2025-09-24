package com.gyl.bys.domain.models;

import com.gyl.bys.domain.VO.*;
import com.gyl.bys.domain.models.abs.BaseBuilder;
import com.gyl.bys.domain.models.abs.DomainEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Refactorizar:
// 1. Eliminar por completo los `setters` públicos. Las modificaciones de estado deben hacerse
//    a través de métodos de negocio con nombres explícitos (ej. `actualizarObservaciones(String obs)`).
// 2. Separar la creación de la reconstitución:
//    - El constructor del `Builder` no debe recibir el `id`. Debe recibir solo los parámetros
//      mínimos y obligatorios para la CREACIÓN de una nueva Búsqueda.
// 3. Crear un `static factory method` llamado `reconstitute(...)` que reciba todos los
//    parámetros (incluido el `id`) para reconstruir un objeto desde la base de datos.

public class Busqueda extends DomainEntity {

    private String nombre;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaFinalizacion;
    private Integer vacantes;
    private String linkJob;
    private String linkJobEditable;
    private String observaciones;
    private Double remuneracion;

    private Cliente cliente;
    private Profesion profesion;
    private ModalidadContratacion modalidadContratacion;
    private Disponibilidad disponibilidad;

    private List<Seniority> seniorities = new ArrayList<>();
    private List<Habilidad> habilidades = new ArrayList<>();
    private List<Usuario> reclutadores = new ArrayList<>();
    private List<HistorialEstadoBusqueda> historialEstados = new ArrayList<>();

    /*Comportamiento*/

    // <--- VERIFICACIÓN DE CICLO DE VIDA ---
    public void cambiarEstado(Estado nuevoEstado) {
        // Validación de pre-condiciones del método
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }
        if (this.fechaFinalizacion != null) {
            throw new IllegalStateException("No se puede cambiar el estado de una búsqueda que ya ha finalizado.");
        }

        // Lógica original
        if (this.historialEstados == null) {
            this.historialEstados = new ArrayList<>();
        }
        this.historialEstados.add(new HistorialEstadoBusqueda(nuevoEstado, LocalDateTime.now()));
        this.fechaActualizacion = LocalDateTime.now();
    }

    /*Getter y Setter */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // <--- VERIFICACIÓN EN SETTER ---
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        // <--- VERIFICACIÓN EN SETTER ---
        if (vacantes == null || vacantes <= 0) {
            throw new IllegalArgumentException("El número de vacantes debe ser un entero positivo.");
        }
        this.vacantes = vacantes;
    }

    public String getLinkJob() {
        return linkJob;
    }

    public void setLinkJob(String linkJob) {
        this.linkJob = linkJob;
    }

    public String getLinkJobEditable() {
        return linkJobEditable;
    }

    public void setLinkJobEditable(String linkJobEditable) {
        this.linkJobEditable = linkJobEditable;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(Double remuneracion) {
        this.remuneracion = remuneracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public ModalidadContratacion getModalidadContratacion() {
        return modalidadContratacion;
    }

    public void setModalidadContratacion(ModalidadContratacion modalidadContratacion) {
        this.modalidadContratacion = modalidadContratacion;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // <--- PROTECCIÓN DE COLECCIONES ---
    public List<Seniority> getSeniorities() {
        return Collections.unmodifiableList(seniorities);
    }

    public void setSeniorities(List<Seniority> seniorities) {
        this.seniorities = (seniorities != null) ? seniorities : new ArrayList<>();
    }

    public List<Habilidad> getHabilidades() {
        return Collections.unmodifiableList(habilidades);
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = (habilidades != null) ? habilidades : new ArrayList<>();
    }

    public List<Usuario> getReclutadores() {
        return Collections.unmodifiableList(reclutadores);
    }

    public void setReclutadores(List<Usuario> reclutadores) {
        this.reclutadores = (reclutadores != null) ? reclutadores : new ArrayList<>();
    }

    public List<HistorialEstadoBusqueda> getHistorialEstados() {
        return Collections.unmodifiableList(historialEstados);
    }

    public void setHistorialEstados(List<HistorialEstadoBusqueda> historialEstados) {
        this.historialEstados = (historialEstados != null) ? historialEstados : new ArrayList<>();
    }


    /*Builder*/

    private Busqueda(Builder builder) {
        super(builder.id);
        this.nombre = builder.nombre;
        this.fechaCreacion = builder.fechaCreacion;
        this.fechaActualizacion = builder.fechaActualizacion;
        this.fechaFinalizacion = builder.fechaFinalizacion;
        this.vacantes = builder.vacantes;
        this.linkJob = builder.linkJob;
        this.linkJobEditable = builder.linkJobEditable;
        this.observaciones = builder.observaciones;
        this.remuneracion = builder.remuneracion;
        this.cliente = builder.cliente;
        this.profesion = builder.profesion;
        this.modalidadContratacion = builder.modalidadContratacion;
        this.disponibilidad = builder.disponibilidad;
        this.seniorities = builder.safeList(builder.seniorities);
        this.habilidades = builder.safeList(builder.habilidades);
        this.reclutadores = builder.safeList(builder.reclutadores);
        this.historialEstados = builder.safeList(builder.historialEstados);
    }

    public static class Builder extends BaseBuilder<Busqueda, Builder> {
        private final Long id;
        private String nombre;
        private LocalDateTime fechaCreacion;
        private LocalDateTime fechaActualizacion;
        private LocalDateTime fechaFinalizacion;
        private Integer vacantes;
        private String linkJob;
        private String linkJobEditable;
        private String observaciones;
        private Double remuneracion;

        private Cliente cliente;
        private Profesion profesion;
        private ModalidadContratacion modalidadContratacion;
        private Disponibilidad disponibilidad;

        private List<Seniority> seniorities = new ArrayList<>();
        private List<Habilidad> habilidades = new ArrayList<>();
        private List<Usuario> reclutadores = new ArrayList<>();
        private List<HistorialEstadoBusqueda> historialEstados = new ArrayList<>();

        public Builder(Long id) {
            this.id = id;
        }

        public Builder nombre(String nombre) { this.nombre = nombre; return self(); }
        public Builder fechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; return self(); }
        public Builder fechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; return self(); }
        public Builder fechaFinalizacion(LocalDateTime fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; return self(); }
        public Builder vacantes(Integer vacantes) { this.vacantes = vacantes; return self(); }
        public Builder linkJob(String linkJob) { this.linkJob = linkJob; return self(); }
        public Builder linkJobEditable(String linkJobEditable) { this.linkJobEditable = linkJobEditable; return self(); }
        public Builder observaciones(String observaciones) { this.observaciones = observaciones; return self(); }
        public Builder remuneracion(Double remuneracion) { this.remuneracion = remuneracion; return self(); }

        public Builder cliente(Cliente cliente) { this.cliente = cliente; return self(); }
        public Builder profesion(Profesion profesion) { this.profesion = profesion; return self(); }
        public Builder modalidadContratacion(ModalidadContratacion modalidadContratacion) { this.modalidadContratacion = modalidadContratacion; return self(); }
        public Builder disponibilidad(Disponibilidad disponibilidad) { this.disponibilidad = disponibilidad; return self(); }

        public Builder seniorities(List<Seniority> seniorities) { this.seniorities = seniorities; return self(); }
        public Builder habilidades(List<Habilidad> habilidades) { this.habilidades = habilidades; return self(); }
        public Builder reclutadores(List<Usuario> reclutadores) { this.reclutadores = reclutadores; return self(); }
        public Builder historialEstados(List<HistorialEstadoBusqueda> historialEstados) { this.historialEstados = historialEstados; return self(); }

        public Builder addSeniority(Seniority s) { this.seniorities.add(s); return self(); }
        public Builder addHabilidad(Habilidad h) { this.habilidades.add(h); return self(); }
        public Builder addReclutador(Usuario u) { this.reclutadores.add(u); return self(); }
        public Builder addHistorialEstado(HistorialEstadoBusqueda he) { this.historialEstados.add(he); return self(); }

        @Override
        protected Busqueda buildEntity() {
            // <--- VERIFICACIONES DE CREACIÓN ---
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalStateException("El nombre de la búsqueda es obligatorio para su creación.");
            }
            if (vacantes == null || vacantes <= 0) {
                throw new IllegalStateException("El número de vacantes debe ser un entero positivo para la creación.");
            }
            if (cliente == null) {
                throw new IllegalStateException("El cliente es un dato obligatorio para la creación de una búsqueda.");
            }
            if (profesion == null) {
                throw new IllegalStateException("La profesión es un dato obligatorio para la creación de una búsqueda.");
            }
            if (historialEstados == null || historialEstados.isEmpty()) {
                throw new IllegalStateException("La búsqueda debe crearse con al menos un estado inicial.");
            }

            return new Busqueda(this);
        }
    }
}