package com.ingco.anticipo.ingco_control_anticipos.collaborator.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ColaboratorDto {
    private int grossSalary;
    private String bossRut;
}
