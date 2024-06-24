package com.ingco.anticipo.ingco_control_anticipos.authenticate.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistByRutValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistByRut {
    String message() default "Ya existe!, Use otro por favor!, o Ingrese a la plataforma!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
