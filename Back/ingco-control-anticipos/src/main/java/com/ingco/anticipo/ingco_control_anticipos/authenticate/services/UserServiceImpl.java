package com.ingco.anticipo.ingco_control_anticipos.authenticate.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.Rol;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.RolRepository;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        //List<Rol> roles = new ArrayList<>();

        optionalRolUser.ifPresent(roles::add);
        if (user.isAdmin()) {
            Optional<Rol> optionalRolAdmin = rolRepository.findByName("ROLE_ADMIN");
            optionalRolAdmin.ifPresent(roles::add);
        }
        // .setRoles(roles); cuando se implemente rol en User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
