package com.ingco.anticipo.ingco_control_anticipos.advance.services;

import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;

public interface AdvanceService {

    List<Advance> findAll();

    Optional<Advance> findById(Long id);

    Advance save(Advance advance);

}
