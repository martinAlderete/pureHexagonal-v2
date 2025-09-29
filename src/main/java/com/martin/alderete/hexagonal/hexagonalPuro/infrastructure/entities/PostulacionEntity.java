package com.martin.alderete.hexagonal.hexagonalPuro.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postulaciones")
public class PostulacionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_postulacion", nullable = false, updatable = false)
    private LocalDateTime fechaPostulacion;
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;
    @OneToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private CandidatoEntity candidato;
    @OneToOne
    @JoinColumn(name = "busqueda_id", nullable = false)
    private BusquedaEntity busqueda;
}
