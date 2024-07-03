package com.ingco.anticipo.ingco_control_anticipos.advance.controllers;

import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.advance.dtos.AdvanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;
import com.ingco.anticipo.ingco_control_anticipos.advance.services.AdvanceService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/advance")
public class AdvanceControllers {

    @Autowired
    private AdvanceService service;

    @GetMapping("/history")
    public List<Advance> list(){
        return service.findAll();
    }

    @GetMapping("/collaborator/history/{id}")
    public List<Advance> listByColaborator(@PathVariable Long id){
        return service.findByIdCollaborator(id);
    }

    @GetMapping("/collaborator/{id}")
    public List<Advance> listByColaboratorCurrentMonth(@PathVariable Long id){
        return service.findByIdCollaboratorAndCurrentMonth(id);
    }

    @GetMapping("/boss/history/{id}")
    public List<Advance> listByBoss(@PathVariable Long id){
        return service.findByDirectlyBossId(id);
    }

    @GetMapping("/boss/{id}")
    public List<Advance> listByBossCurrentMonth(@PathVariable Long id){
        return service.findByDirectlyBossIdAndCurrentMonth(id);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Advance> advanceOptional = service.findById(id);
        if(advanceOptional.isPresent()) {
            return ResponseEntity.ok(advanceOptional.get());

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la peticion de anticipo o no existe en el sistema");
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Advance advance){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(advance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable Long id, @Valid @RequestBody AdvanceDto advanceDto){
        Optional<Advance> advanceOptional = service.findById(id);
        if(advanceOptional.isPresent()) {
            Advance advanceDB = advanceOptional.get();
            if (!(advanceDto.getStatus() == null)){
                advanceDB.setEstado(advanceDto.getStatus());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(service.update(advanceDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la peticion de anticipo o no existe en el sistema");
    }
}
