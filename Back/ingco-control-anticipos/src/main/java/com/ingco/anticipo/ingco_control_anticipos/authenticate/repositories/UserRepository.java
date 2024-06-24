package com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByRut(String email);

    Optional<User> findByRut(String rut);
}
