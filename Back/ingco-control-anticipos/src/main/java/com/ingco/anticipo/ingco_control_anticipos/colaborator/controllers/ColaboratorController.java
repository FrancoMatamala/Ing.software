package com.ingco.anticipo.ingco_control_anticipos.colaborator.controllers;

import com.ingco.anticipo.ingco_control_anticipos.colaborator.dtos.ColaboratorDto;
import com.ingco.anticipo.ingco_control_anticipos.colaborator.entities.Colaborator;
import com.ingco.anticipo.ingco_control_anticipos.colaborator.services.ColaboratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/colaborador")
public class ColaboratorController {

    @Autowired
    private ColaboratorService colaboratorService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(colaboratorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Colaborator> colaboratorOptional = colaboratorService.getColaboratorById(id);
        if (colaboratorOptional.isPresent()){
            return ResponseEntity.ok(colaboratorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Colaborator colaborator, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(colaboratorService.save(colaborator));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ColaboratorDto colaboratorDto, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Colaborator> colaboratorOptional = colaboratorService.getColaboratorById(id);
        if (colaboratorOptional.isPresent()){
            Colaborator colaboratorDB = colaboratorOptional.get();
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
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
