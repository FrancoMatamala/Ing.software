package com.ingco.anticipo.ingco_control_anticipos.proyect.service;

import com.ingco.anticipo.ingco_control_anticipos.proyect.entities.Proyect;
import com.ingco.anticipo.ingco_control_anticipos.proyect.repositories.ProyectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectServiceImpl implements ProyectService{

    @Autowired
    private ProyectRepository proyectRepository;

    @Override
    public List<Proyect> listAll() {
        return (List<Proyect>) proyectRepository.findAll();
    }

    @Override
    public Optional<Proyect> findProyect(Long id) {
        return proyectRepository.findById(id);
    }

    @Override
    public Proyect save(Proyect proyect) {
        return proyectRepository.save(proyect);
    }

    @Override
    public void delete(Long id) {
        proyectRepository.deleteById(id);
    }
}
