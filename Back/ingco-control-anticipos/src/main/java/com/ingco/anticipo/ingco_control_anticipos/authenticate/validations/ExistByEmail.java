package com.ingco.anticipo.ingco_control_anticipos.authenticate.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistByEmailValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistByEmail {
    String message() default "no valido!, Use otro por favor!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
