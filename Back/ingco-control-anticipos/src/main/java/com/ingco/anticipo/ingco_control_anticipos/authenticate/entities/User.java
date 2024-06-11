package com.ingco.anticipo.ingco_control_anticipos.authenticate.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.validations.ExistByEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "nombre")
    private String name;

    @NotEmpty
    @Column(name = "apellido")
    private String lastName;

    @ExistByEmail
    @NotEmpty
    @Column(name = "correo_electronico")
    private String email;

    @NotEmpty
    @Column(name ="rut", unique = true)
    private String rut;

    @NotEmpty
    @Column(name = "contraseña")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwd;

    @Column(name = "estado")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @PrePersist
    public void PrePersist() {
        enabled = true;
    }
}