package com.ingco.anticipo.ingco_control_anticipos.project.service;

import com.ingco.anticipo.ingco_control_anticipos.project.entities.Project;
import com.ingco.anticipo.ingco_control_anticipos.project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository proyectRepository;

    @Override
    public List<Project> listAll() {
        return (List<Project>) proyectRepository.findAll();
    }

    @Override
    public Optional<Project> findProyect(Long id) {
        return proyectRepository.findById(id);
    }

    @Override
    public Project save(Project proyect) {
        return proyectRepository.save(proyect);
    }

    @Override
    public void delete(Long id) {
        proyectRepository.deleteById(id);
    }
}
