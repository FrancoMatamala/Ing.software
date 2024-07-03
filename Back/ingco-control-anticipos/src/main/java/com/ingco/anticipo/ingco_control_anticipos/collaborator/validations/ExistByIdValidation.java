package com.ingco.anticipo.ingco_control_anticipos.collaborator.validations;

import com.ingco.anticipo.ingco_control_anticipos.collaborator.services.CollaboratorService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistByIdValidation implements ConstraintValidator<ExistById, Long> {

    @Autowired
    private CollaboratorService colaboratorService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return !colaboratorService.ExistById(id);
    }
}
