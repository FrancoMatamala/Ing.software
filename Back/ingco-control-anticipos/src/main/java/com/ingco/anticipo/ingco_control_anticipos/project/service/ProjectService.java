package com.ingco.anticipo.ingco_control_anticipos.project.service;

import com.ingco.anticipo.ingco_control_anticipos.project.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> listAll();

    Optional<Project> findProyect(Long id);

    Project save(Project proyect);

    void delete(Long id);
}
