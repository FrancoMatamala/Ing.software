package com.ingco.anticipo.ingco_control_anticipos.advance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;

public interface AdvanceRepository extends CrudRepository<Advance, Long> {

    @Query("SELECT COUNT(a) FROM Advance a WHERE a.collaborator.user.rut = ?1 AND MONTH(a.dateEntry) = MONTH(CURRENT_DATE) AND YEAR(a.dateEntry) = YEAR(CURRENT_DATE)")
    int countByRutCollaboratorAndCurrentMonth(String rutColaboradores);

    @Query("SELECT a FROM Advance a WHERE a.collaborator.directlyBoss.id = ?1")
    List<Advance> findByDirectlyBossId(Long bossId);

    @Query("SELECT a FROM Advance a WHERE a.collaborator.directlyBoss.id = ?1 AND MONTH(a.dateEntry) = MONTH(CURRENT_DATE) AND YEAR(a.dateEntry) = YEAR(CURRENT_DATE)")
    List<Advance> findByDirectlyBossIdAndCurrentMonth(Long bossId);

    @Query("SELECT a FROM Advance a WHERE a.collaborator.user.id = ?1")
    List<Advance> findByRutCollaborator(Long ColaboratorId);

    @Query("SELECT a FROM Advance a WHERE a.collaborator.user.id = ?1 AND MONTH(a.dateEntry) = MONTH(CURRENT_DATE) AND YEAR(a.dateEntry) = YEAR(CURRENT_DATE)")
    List<Advance> findByRutCollaboratorAndCurrentMonth(Long ColaboratorId);
}
