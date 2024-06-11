package com.practica.crud.practicacrudrest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.practica.crud.practicacrudrest.Entities.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUserName(String username);

    Optional<User> findByUserName(String userName);
}
