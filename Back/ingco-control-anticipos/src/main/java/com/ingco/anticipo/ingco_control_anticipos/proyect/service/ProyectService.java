package com.ingco.anticipo.ingco_control_anticipos.proyect.service;

import com.ingco.anticipo.ingco_control_anticipos.proyect.entities.Proyect;

import java.util.List;
import java.util.Optional;

public interface ProyectService {

    List<Proyect> listAll();

    Optional<Proyect> findProyect(Long id);

    Proyect save(Proyect proyect);

    void delete(Long id);
}
