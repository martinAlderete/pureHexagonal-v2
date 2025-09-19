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


    private String nombre;

    private String apellido;

    private int cuil;

    private int dni;

    private String email;

    private String linkedin;

    private String observaciones;

    private int telefono;

    private LocalDate fecha_nacimiento;

    private LocalDate fecha_contacto;

    @OneToMany(mappedBy = "candidato")
    private List<PostulacionEntity> postulaciones;

}
