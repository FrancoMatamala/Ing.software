package com.ingco.anticipo.ingco_control_anticipos.collaborator.services;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Colaborator;

import java.util.List;
import java.util.Optional;

public interface ColaboratorService {

    List<Colaborator> findAll();

    Optional<Colaborator> getColaboratorById(Long id);

    Optional<User> getBossByRut(String rut);

    Colaborator save(Colaborator colaborator);
}
