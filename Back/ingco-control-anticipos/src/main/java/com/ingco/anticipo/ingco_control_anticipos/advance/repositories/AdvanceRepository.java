package com.ingco.anticipo.ingco_control_anticipos.advance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;

public interface AdvanceRepository extends CrudRepository<Advance, Long> {
    
    Optional<Advance> findById(Long id);

    @Query("SELECT COUNT(a) FROM Advance a WHERE a.colaborator.id = :colaboratorId")
    int countByColaboratorId(@Param("colaboratorId") Long colaboratorId);

    @Query("SELECT COUNT(a) FROM Advance a WHERE a.colaborator.id = :colaboratorId AND MONTH(a.fechaIngreso) = MONTH(CURRENT_DATE) AND YEAR(a.fechaIngreso) = YEAR(CURRENT_DATE)")
    int countByColaboratorIdAndCurrentMonth(@Param("colaboratorId") Long colaboratorId);
}
