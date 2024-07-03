package com.ingco.anticipo.ingco_control_anticipos.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proyectos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String name;

    @NotBlank
    @Column(name = "ubicacion")
    private String locate;

    @NotBlank
    @Column(name = "tipo_de_trabajo")
    private String workType;
}
