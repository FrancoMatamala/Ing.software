package com.ingco.anticipo.ingco_control_anticipos.project.controller;

import com.ingco.anticipo.ingco_control_anticipos.project.dto.ProjectDto;
import com.ingco.anticipo.ingco_control_anticipos.project.entities.Project;
import com.ingco.anticipo.ingco_control_anticipos.project.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(projectService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Project> projectOptional = projectService.findProyect(id);
        if (projectOptional.isPresent()){
            return ResponseEntity.ok(projectOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el proyecto o no existe en el sistema");
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Project proyect){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(proyect));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody ProjectDto proyectDto) {
        Optional<Project> projectOptional = projectService.findProyect(id);
        if (projectOptional.isPresent()){
            Project proyectDB = projectOptional.get();
            if (!proyectDto.getName().isEmpty()) {
                proyectDB.setName(proyectDto.getName());
            }
            if (!proyectDto.getWorkType().isEmpty()){
                proyectDB.setWorkType(proyectDto.getWorkType());
            }
            if (!proyectDto.getLocate().isEmpty()){
                proyectDB.setLocate(proyectDto.getLocate());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(proyectDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el proyecto o no existe en el sistema");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Project> projectOptional = projectService.findProyect(id);
        if (projectOptional.isPresent()){
            projectService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el proyecto o no existe en el sistema");
    }
}
