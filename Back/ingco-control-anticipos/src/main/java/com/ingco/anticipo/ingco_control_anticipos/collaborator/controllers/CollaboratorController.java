package com.ingco.anticipo.ingco_control_anticipos.collaborator.controllers;

import com.ingco.anticipo.ingco_control_anticipos.collaborator.dtos.CollaboratorDto;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.services.CollaboratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/collaborator")
public class CollaboratorController {

    @Autowired
    private CollaboratorService colaboratorService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(colaboratorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Collaborator> collaboratorOptional = colaboratorService.getColaboratorById(id);
        if (collaboratorOptional.isPresent()){
            return ResponseEntity.ok(collaboratorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Collaborator colaborator){
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboratorService.save(colaborator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CollaboratorDto colaboratorDto) {
        Optional<Collaborator> collaboratorOptional = colaboratorService.getColaboratorById(id);
        if (collaboratorOptional.isPresent()){
            Collaborator colaboratorDB = collaboratorOptional.get();
            if (colaboratorDto.getGrossSalary() <= 10000000 && colaboratorDto.getGrossSalary() > 100000) {
                colaboratorDB.setGrossSalary(colaboratorDto.getGrossSalary());
            }
            if (!colaboratorDto.getBossRut().isEmpty()) {
                colaboratorDB.setBossRut(colaboratorDto.getBossRut());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(colaboratorService.save(colaboratorDB));
        }
        return ResponseEntity.notFound().build();
    }
}
