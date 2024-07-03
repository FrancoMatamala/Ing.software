package com.ingco.anticipo.ingco_control_anticipos.collaborator.repositories;

import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Long> {

    @Query("SELECT c FROM Collaborator c WHERE c.directlyBoss.id = ?1")
    List<Collaborator> findByBossId(Long bossId);
}

