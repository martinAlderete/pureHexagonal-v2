package com.gyl.bys.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList; // Usar ArrayList o HashSet para inicializar
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "grupo_favorito")
public class GrupoFavoritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    //Ahora esta entidad es la dueña de la relación ManyToMany
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "candidato_grupo_favorito",
            joinColumns = @JoinColumn(name = "grupo_favorito_id"),
            inverseJoinColumns = @JoinColumn(name = "candidato_id")
    )
    private List<CandidatoEntity> candidatos = new ArrayList<>();
}