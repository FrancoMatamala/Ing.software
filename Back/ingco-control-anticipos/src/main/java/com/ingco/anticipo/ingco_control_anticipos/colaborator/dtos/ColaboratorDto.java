package com.ingco.anticipo.ingco_control_anticipos.colaborator.dtos;

import com.ingco.anticipo.ingco_control_anticipos.proyect.entities.Proyect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ColaboratorDto {
    private int grossSalary;
    private String bossRut;
    private Long proyectId;
}
