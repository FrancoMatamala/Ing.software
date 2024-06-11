package com.practica.crud.practicacrudrest.services;

import java.util.List;

import com.practica.crud.practicacrudrest.Entities.User;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUserName(String username);
}
