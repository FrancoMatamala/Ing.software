package com.ingco.anticipo.ingco_control_anticipos.collaborator.controllers;

import com.ingco.anticipo.ingco_control_anticipos.collaborator.dtos.CollaboratorDto;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.services.CollaboratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/collaborator")
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(collaboratorService.findAll());
    }

    @GetMapping("/boss/{bossId}")
    public ResponseEntity<?> findByBossId(@PathVariable Long bossId) {
        List<Collaborator> collaborators = collaboratorService.listByBossId(bossId);
        if (!collaborators.isEmpty()) {
            return ResponseEntity.ok(collaborators);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Jefe o no existe en el sistema ");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Collaborator> collaboratorOptional = collaboratorService.getColaboratorById(id);
        if (collaboratorOptional.isPresent()){
            return ResponseEntity.ok(collaboratorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Collaborator colaborator){
        return ResponseEntity.status(HttpStatus.CREATED).body(collaboratorService.save(colaborator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CollaboratorDto colaboratorDto) {
        Optional<Collaborator> collaboratorOptional = collaboratorService.getColaboratorById(id);
        if (collaboratorOptional.isPresent()){
            Collaborator colaboratorDB = collaboratorOptional.get();
            if (colaboratorDto.getGrossSalary() <= 10000000 && colaboratorDto.getGrossSalary() > 100000) {
                colaboratorDB.setGrossSalary(colaboratorDto.getGrossSalary());
            }
            if (!colaboratorDto.getBossRut().isEmpty()) {
                colaboratorDB.setBossRut(colaboratorDto.getBossRut());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(collaboratorService.save(colaboratorDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el colaborador o no existe en el sistema");
    }
}
