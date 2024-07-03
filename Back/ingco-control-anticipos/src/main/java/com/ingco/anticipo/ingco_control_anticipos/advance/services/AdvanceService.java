package com.ingco.anticipo.ingco_control_anticipos.advance.services;

import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;

public interface AdvanceService {

    List<Advance> findAll();

    Optional<Advance> findById(Long id);

    Advance save(Advance advance);

    Advance update(Advance advance);

    List<Advance> findByIdCollaborator(Long ColaboratorId);

    List<Advance> findByIdCollaboratorAndCurrentMonth(Long ColaboratorId);

    List<Advance> findByDirectlyBossId(Long bossId);

    List<Advance> findByDirectlyBossIdAndCurrentMonth(Long id);
}
