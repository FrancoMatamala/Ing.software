package com.ingco.anticipo.ingco_control_anticipos.global.hadlerExeptionError.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ErrorDto {
    private String error;
    private String message;
    private int status;
    private Date date;
}
