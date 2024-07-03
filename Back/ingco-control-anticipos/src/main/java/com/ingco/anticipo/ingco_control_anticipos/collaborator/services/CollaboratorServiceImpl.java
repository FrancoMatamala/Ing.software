package com.ingco.anticipo.ingco_control_anticipos.collaborator.services;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.UserRepository;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.repositories.CollaboratorRepository;
import com.ingco.anticipo.ingco_control_anticipos.project.entities.Project;
import com.ingco.anticipo.ingco_control_anticipos.project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollaboratorRepository colaboratorRepository;

    @Autowired
    private ProjectRepository proyectRepository;

    @Override
    public List<Collaborator> findAll() {
        return (List<Collaborator>) colaboratorRepository.findAll();
    }

    @Override
    public List<Collaborator> listByBossId(Long bossId) {
        return colaboratorRepository.findByBossId(bossId);
    }

    @Override
    public Optional<Collaborator> getColaboratorById(Long id) {
        return colaboratorRepository.findById(id);
    }

    @Override
    public Optional<User> getBossByRut(String rut) {
        return userRepository.findByRut(rut);
    }

    @Override
    public Collaborator save(Collaborator collaborator) {
        Optional<User> userOptional = userRepository.findById(collaborator.getIdUser());
        User user = userOptional.orElseThrow(() -> new NullPointerException("El usuario no existe"));

        if (!user.getRol().getName().equals("ROLE_COLLABORATOR")) {
            throw new IllegalArgumentException("El usuario debe tener el rol de Colaborador");
        }
        collaborator.setUser(user);

        Optional<User> bossOptional = userRepository.findByRut(collaborator.getBossRut());
        User boss = bossOptional.orElseThrow(() -> new NullPointerException("El jefe no existe"));

        if (!boss.getRol().getName().equals("ROLE_BOSS")) {
            throw new IllegalArgumentException("El usuario debe tener el rol de Jefe");
        }
        collaborator.setDirectlyBoss(boss);

        Optional<Project> proyectOptional = proyectRepository.findById(collaborator.getProyectId());
        Project proyect = proyectOptional.orElseThrow(() -> new NullPointerException("El proyecto no existe"));
        collaborator.setProject(proyect);

        Date dateEntry = collaborator.getDateEntry();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateEntry);
        calendar.add(Calendar.YEAR, 5);
        Date fiveYearsAfterEntry = calendar.getTime();
        collaborator.setAdditionalPayment(new Date().after(fiveYearsAfterEntry));

        System.out.println("Paso: y guardo ");
        return colaboratorRepository.save(collaborator);
    }

    @Override
    public Boolean ExistById(Long id) {
        return colaboratorRepository.existsById(id);
    }
}
