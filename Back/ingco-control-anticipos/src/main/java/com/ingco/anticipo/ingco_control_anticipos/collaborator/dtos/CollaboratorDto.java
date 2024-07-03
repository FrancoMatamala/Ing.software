package com.ingco.anticipo.ingco_control_anticipos.collaborator.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CollaboratorDto {
    private int grossSalary;
    private String bossRut;
    private Long proyectId;
}
