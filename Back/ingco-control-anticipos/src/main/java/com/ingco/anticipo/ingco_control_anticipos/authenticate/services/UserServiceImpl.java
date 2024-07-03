package com.ingco.anticipo.ingco_control_anticipos.authenticate.services;

import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.Role;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.RoleRepository;
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
    private RoleRepository rolRepository;
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

        Optional<Role> optionalRole = rolRepository.findByName("ROLE_COLLABORATOR");
        if (user.isAdmin()) {
            optionalRole = rolRepository.findByName("ROLE_ADMIN");
        } else if (user.isBoss()) {
            optionalRole = rolRepository.findByName("ROLE_BOSS");
        }
        if (optionalRole.isPresent()) {
            user.setRol(optionalRole.get());
        } else {
            throw new RuntimeException("El rol especificado no fue encontrado");
        }

        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existByEmail(String rut) {
        return userRepository.existsByRut(rut);
    }

}
