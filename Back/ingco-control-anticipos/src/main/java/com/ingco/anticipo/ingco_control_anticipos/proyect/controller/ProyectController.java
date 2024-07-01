package com.ingco.anticipo.ingco_control_anticipos.proyect.controller;

import com.ingco.anticipo.ingco_control_anticipos.proyect.dto.ProyectDto;
import com.ingco.anticipo.ingco_control_anticipos.proyect.entities.Proyect;
import com.ingco.anticipo.ingco_control_anticipos.proyect.service.ProyectService;
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
@RequestMapping("/api/proyect")
public class ProyectController {

    @Autowired
    private ProyectService proyectService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(proyectService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Proyect> proyectOptional = proyectService.findProyect(id);
        if (proyectOptional.isPresent()){
            return ResponseEntity.ok(proyectOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Proyect proyect, BindingResult result){
        if (result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectService.save(proyect));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody ProyectDto proyectDto, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<Proyect> proyectOptional = proyectService.findProyect(id);
        if (proyectOptional.isPresent()){
            Proyect proyectDB = proyectOptional.get();
            if (!proyectDto.getName().isEmpty()) {
                proyectDB.setName(proyectDto.getName());
            }
            if (!proyectDto.getWorkType().isEmpty()){
                proyectDB.setWorkType(proyectDto.getWorkType());
            }
            if (!proyectDto.getLocate().isEmpty()){
                proyectDB.setLocate(proyectDto.getLocate());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(proyectService.save(proyectDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Proyect> proyectOptional = proyectService.findProyect(id);
        if (proyectOptional.isPresent()){
            proyectService.delete(id);
            return ResponseEntity.noContent().build();
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
