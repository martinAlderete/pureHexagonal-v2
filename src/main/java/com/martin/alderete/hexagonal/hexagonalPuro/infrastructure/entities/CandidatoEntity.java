package com.gyl.bys.infrastructure.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "candidato")
public class CandidatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int cuil;

    @Column(nullable = false)
    private int dni;

    @Column(nullable = false, unique = true)
    private String email;

    private String linkedin;

    private String observaciones;

    private int telefono;

    private LocalDate fecha_nacimiento;

    private LocalDate fecha_contacto;

    @OneToMany(mappedBy = "candidato")
    private List<PostulacionEntity> postulaciones;

}
