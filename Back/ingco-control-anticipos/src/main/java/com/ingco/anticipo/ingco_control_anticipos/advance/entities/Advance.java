package com.ingco.anticipo.ingco_control_anticipos.advance.entities;
import com.ingco.anticipo.ingco_control_anticipos.colaborator.entities.Colaborator;
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
    private Colaborator colaborator;


    @NotNull
    @Column(name = "fecha_ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date fechaIngreso;

    @NotNull
    @Column(name = "monto_solicitado")
    private Integer requestedamount;

    @NotBlank
    @Column(name = "motivo")
    private String motivo;
    
    @NotNull
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idColaborator;

    @Override
    public String toString() {
        return "LoanRequest{" +
                "id=" + id +
                ", fechaIngreso=" + fechaIngreso +
                ", montoSolicitado=" + requestedamount +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
