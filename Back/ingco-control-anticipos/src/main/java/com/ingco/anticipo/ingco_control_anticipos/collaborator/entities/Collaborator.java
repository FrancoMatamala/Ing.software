package com.ingco.anticipo.ingco_control_anticipos.collaborator.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.validations.ExistById;
import com.ingco.anticipo.ingco_control_anticipos.project.entities.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "colaboradores")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Collaborator {

    @Id
    @ExistById
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private User user;

    @Min(100000)
    @Max(10000000)
    @NotNull
    @Column(name = "salario_bruto")
    private Integer grossSalary;

    @Column(name = "estado_pago_adicional")
    private Boolean additionalPayment;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "fecha_ingreso")
    private Date dateEntry;

    @ManyToOne
    @JoinColumn(name = "jefe_directo_id", nullable = false)
    private User direclyBoss;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Project proyect;

    @Transient
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idUser;

    @Transient
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String BossRut;

    @Transient
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long proyectId;

    @PrePersist
    public void prePersist() {

        id = user.getId();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateEntry);
        calendar.add(Calendar.YEAR, 5);
        Date fiveYearsAfterEntry = calendar.getTime();
        additionalPayment = new Date().after(fiveYearsAfterEntry);
    }
}
