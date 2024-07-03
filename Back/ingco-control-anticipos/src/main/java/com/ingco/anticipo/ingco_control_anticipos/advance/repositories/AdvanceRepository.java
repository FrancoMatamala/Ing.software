package com.ingco.anticipo.ingco_control_anticipos.advance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;

public interface AdvanceRepository extends CrudRepository<Advance, Long> {

    @Query("SELECT COUNT(a) FROM Advance a WHERE a.colaborator.id = :colaboratorId AND MONTH(a.dateEntry) = MONTH(CURRENT_DATE) AND YEAR(a.dateEntry) = YEAR(CURRENT_DATE)")
    int countByColaboratorIdAndCurrentMonth(@Param("colaboratorId") Long colaboratorId);
}
