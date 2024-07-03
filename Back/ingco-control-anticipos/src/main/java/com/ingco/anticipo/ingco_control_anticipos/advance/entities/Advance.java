package com.ingco.anticipo.ingco_control_anticipos.advance.entities;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "anticipo")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Advance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborator_id", nullable = false)
    private Collaborator colaborator;


    @Column(name = "fecha_ingreso", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date dateEntry;

    @NotNull
    @Column(name = "monto_solicitado")
    private Integer requestAdAmount;

    @NotBlank
    @Column(name = "motivo")
    private String motive;
    
    @NotNull
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idColaborator;

    @Override
    public String toString() {
        return "LoanRequest{" +
                "id=" + id +
                ", fechaIngreso=" + dateEntry +
                ", montoSolicitado=" + requestAdAmount +
                ", motivo='" + motive + '\'' +
                '}';
    }
}
