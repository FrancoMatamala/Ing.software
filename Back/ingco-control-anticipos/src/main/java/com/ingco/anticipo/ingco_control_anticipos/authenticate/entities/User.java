package com.ingco.anticipo.ingco_control_anticipos.authenticate.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.validations.ExistByRut;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @NotEmpty
    @Email
    @Column(name = "correo_electronico")
    private String email;

    @ExistByRut
    @NotEmpty
    @Column(name = "rut", unique = true)
    private String rut;

    @NotEmpty
    @Column(name = "contraseña")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwd;

    @Column(name = "estado")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role rol;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean boss;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean collaborator;

    @PrePersist
    public void PrePersist() {
        enabled = true;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", rut='" + rut + '\'' +
                ", passwd='" + passwd + '\'' +
                ", enabled=" + enabled +
                ", rol=" + rol +
                ", admin=" + admin +
                '}';
    }
}