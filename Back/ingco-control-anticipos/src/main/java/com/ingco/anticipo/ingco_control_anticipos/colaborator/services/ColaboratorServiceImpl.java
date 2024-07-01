package com.ingco.anticipo.ingco_control_anticipos.colaborator.services;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.UserRepository;
import com.ingco.anticipo.ingco_control_anticipos.colaborator.entities.Colaborator;
import com.ingco.anticipo.ingco_control_anticipos.colaborator.repositories.ColaboratorRepository;
import com.ingco.anticipo.ingco_control_anticipos.proyect.entities.Proyect;
import com.ingco.anticipo.ingco_control_anticipos.proyect.repositories.ProyectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboratorServiceImpl implements ColaboratorService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ColaboratorRepository colaboratorRepository;

    @Autowired
    private ProyectRepository proyectRepository;

    @Override
    public List<Colaborator> findAll() {
        return (List<Colaborator>) colaboratorRepository.findAll();
    }

    @Override
    public Optional<Colaborator> getColaboratorById(Long id) {
        return colaboratorRepository.findById(id);
    }

    @Override
    public Optional<User> getBossByRut(String rut) {
        return userRepository.findByRut(rut);
    }

    @Override
    public Colaborator save(Colaborator colaborator) {
        Optional<User> userOptional = userRepository.findById(colaborator.getUser().getId());
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

        if (!user.getRol().getName().equals("ROLE_COLLABORATOR")) {
            throw new IllegalArgumentException("El usuario debe tener el rol de Colaborador");
        }
        colaborator.setUser(user);

        Optional<User> bossOptional = userRepository.findByRut(colaborator.getBossRut());
        User boss = bossOptional.orElseThrow(() -> new IllegalArgumentException("El jefe no existe"));

        if (!boss.getRol().getName().equals("ROLE_BOSS")) {
            throw new IllegalArgumentException("El jefe debe tener el rol de Jefe");
        }
        colaborator.setDireclyBoss(boss);

        Optional<Proyect> proyectOptional = proyectRepository.findById(colaborator.getProyectId());
        Proyect proyect = proyectOptional.orElseThrow(() -> new IllegalArgumentException("El proyecto no existe"));
        colaborator.setProyect(proyect);

        Date dateEntry = colaborator.getDateEntry();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateEntry);
        calendar.add(Calendar.YEAR, 5);
        Date fiveYearsAfterEntry = calendar.getTime();
        colaborator.setAdditionalPayment(new Date().after(fiveYearsAfterEntry));

        System.out.println("Paso: y guardo ");
        return colaboratorRepository.save(colaborator);
    }
}
