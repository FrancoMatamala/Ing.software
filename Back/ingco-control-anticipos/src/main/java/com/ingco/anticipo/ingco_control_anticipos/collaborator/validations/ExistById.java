package com.ingco.anticipo.ingco_control_anticipos.collaborator.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistByIdValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistById {
    String message() default "Ya existe como colaborador!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
