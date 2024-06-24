package com.ingco.anticipo.ingco_control_anticipos.authenticate.services;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.Role;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByRut(rut);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Rut %s no existe en el sistema!", rut));
        }

        User user = userOptional.orElseThrow();

        Role role = user.getRol();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());

        List<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPasswd(),
                user.isEnabled(),
                true,
                true,
                true,
                authorities);
    }
}
