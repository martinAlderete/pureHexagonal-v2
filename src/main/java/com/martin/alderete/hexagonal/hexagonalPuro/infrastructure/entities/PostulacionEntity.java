package com.gyl.bys.infrastructure.entities;

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
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_postulacion")
    private LocalDateTime fechaPostulacion;
    @OneToOne
    private UsuarioEntity usuario;
    @OneToOne
    private CandidatoEntity candidato;
    @OneToOne
    private BusquedaEntity busqueda;
}
