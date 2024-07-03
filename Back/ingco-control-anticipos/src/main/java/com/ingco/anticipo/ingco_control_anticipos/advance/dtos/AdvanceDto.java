package com.ingco.anticipo.ingco_control_anticipos.advance.dtos;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AdvanceDto {
    private Advance.Status status;
}