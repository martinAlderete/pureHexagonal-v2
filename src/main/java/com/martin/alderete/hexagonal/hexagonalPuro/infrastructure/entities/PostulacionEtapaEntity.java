package com.gyl.bys.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "postulacion_etapa")
public class PostulacionEtapaEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;
    @ManyToOne
    private EtapaEntity etapa;
    @ManyToOne
    private PostulacionEntity postulacion;
}
