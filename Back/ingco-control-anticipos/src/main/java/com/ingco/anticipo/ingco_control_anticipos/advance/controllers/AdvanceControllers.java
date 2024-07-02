package com.ingco.anticipo.ingco_control_anticipos.advance.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;
import com.ingco.anticipo.ingco_control_anticipos.advance.services.AdvanceService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/advance")
public class AdvanceControllers {

    @Autowired
    private AdvanceService service;

    public List<Advance> list(){
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Advance> advanceOptional = service.findById(id);
        if(advanceOptional.isPresent()) {
            return ResponseEntity.ok(advanceOptional.orElseThrow());

        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Advance advance, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(advance));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
