package com.practica.crud.practicacrudrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.crud.practicacrudrest.Entities.Rol;
import com.practica.crud.practicacrudrest.Entities.User;
import com.practica.crud.practicacrudrest.repositories.RolRepository;
import com.practica.crud.practicacrudrest.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {

        Optional<Rol> optionalRolUser = rolRepository.findByName("ROLE_USER");
        List<Rol> roles = new ArrayList<>();

        optionalRolUser.ifPresent(roles::add);
        if (user.isAdmin()) {
            Optional<Rol> optionalRolAdmin = rolRepository.findByName("ROLE_ADMIN");
            optionalRolAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }

}
