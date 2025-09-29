package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "busqueda")
public class BusquedaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name = "fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;

    private Integer vacantes;

    @Column(name = "link_job")
    private String linkJob;

    @Column(name = "link_job_editable")
    private String linkJobEditable;

    private String observaciones;

    private Double remuneracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesion_id")
    private ProfesionEntity profesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modalidad_contratacion_id")
    private ModalidadContratacionEntity modalidadContratacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disponibilidad_id")
    private DisponibilidadEntity disponibilidad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "busqueda_seniority",
            joinColumns = @JoinColumn(name = "busqueda_id"),
            inverseJoinColumns = @JoinColumn(name = "seniority_id")
    )
    private Set<SeniorityEntity> seniorities = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "busqueda_habilidades",
            joinColumns = @JoinColumn(name = "busqueda_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidad_id")
    )
    private Set<HabilidadEntity> habilidades = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "busqueda_reclutador",
            joinColumns = @JoinColumn(name = "busqueda_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<UsuarioEntity> reclutadores = new HashSet<>();

    @OneToMany(
            mappedBy = "busqueda",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<HistorialEstadoBusquedaEntity> historialEstados;

}