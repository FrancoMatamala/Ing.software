package com.ingco.anticipo.ingco_control_anticipos.authenticate.validations;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByEmailValidation implements ConstraintValidator<ExistByEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (userService == null) {
            return true;
        }
        return !userService.existsByEmail(email);
    }

}
