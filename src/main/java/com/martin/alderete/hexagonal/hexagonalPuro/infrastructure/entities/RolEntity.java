package com.gyl.bys.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@Table(name = "rol")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public RolEntity() {
    }

    public RolEntity(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDateTime.now();
    }


}
