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
@Table(name = "busqueda_estado")
public class HistorialEstadoBusquedaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "busqueda_id", nullable = false)
    private BusquedaEntity busqueda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoEntity estado;

    @Column(name = "fecha")
    private LocalDateTime fecha;


}