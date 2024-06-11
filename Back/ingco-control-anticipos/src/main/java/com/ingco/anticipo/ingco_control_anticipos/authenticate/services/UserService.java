package com.ingco.anticipo.ingco_control_anticipos.authenticate.services;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByEmail(String username);
}
