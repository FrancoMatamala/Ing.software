package com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories;

import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
