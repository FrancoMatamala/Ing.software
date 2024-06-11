package com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories;

import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Long> {

    Optional<Rol> findByName(String name);
}
