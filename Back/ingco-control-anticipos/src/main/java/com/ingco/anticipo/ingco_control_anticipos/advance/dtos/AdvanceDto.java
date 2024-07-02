package com.ingco.anticipo.ingco_control_anticipos.advance.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AdvanceDto {

    @NotNull
    private Long idColaborator;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date fechaIngreso;

    @NotNull
    private Integer requestedamount;

    @NotBlank
    private String motivo;
}