package com.ingco.anticipo.ingco_control_anticipos.collaborator.services;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;

import java.util.List;
import java.util.Optional;

public interface CollaboratorService {

    List<Collaborator> findAll();

    Optional<Collaborator> getColaboratorById(Long id);

    Optional<User> getBossByRut(String rut);

    Collaborator save(Collaborator collaborator);

    Boolean ExistById(Long id);
}
