package com.practica.crud.practicacrudrest.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.practica.crud.practicacrudrest.Entities.Rol;

public interface RolRepository extends CrudRepository<Rol, Long> {

    Optional<Rol> findByName(String name);
}
