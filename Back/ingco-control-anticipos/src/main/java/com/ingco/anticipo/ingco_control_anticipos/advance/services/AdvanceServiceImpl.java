package com.ingco.anticipo.ingco_control_anticipos.advance.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.collaborator.entities.Collaborator;
import com.ingco.anticipo.ingco_control_anticipos.collaborator.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ingco.anticipo.ingco_control_anticipos.advance.entities.Advance;
import com.ingco.anticipo.ingco_control_anticipos.advance.repositories.AdvanceRepository;

@Service
public class AdvanceServiceImpl implements AdvanceService {

    @Autowired
    private AdvanceRepository repository;

    @Autowired
    private CollaboratorRepository colaboratorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Advance> findAll() {
        return (List<Advance>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Advance> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Advance save(Advance advance) {
        LocalDate today = LocalDate.now();
        int currentDay = today.getDayOfMonth();

        if (currentDay > 10) {
            throw new IllegalArgumentException("Los anticipos solo pueden ser solicitados entre el 1 y el 10 de cada mes.");
        }

        advance.setDateEntry(java.sql.Date.valueOf(today));

        Optional<Collaborator> colaboratorOptional = colaboratorRepository.findById(advance.getIdColaborator());
        Collaborator colaborator = colaboratorOptional.orElseThrow(() -> new NullPointerException("El colaborador no existe"));

        advance.setColaborator(colaborator);

        int advanceCount = repository.countByColaboratorIdAndCurrentMonth(colaborator.getId());
        if (advanceCount >= 2) {
            throw new IllegalArgumentException("El colaborador excede la cantidad de anticipos solicitados en este mes.");
        }

        Integer grossSalary = colaborator.getGrossSalary();

        Double maxAllowedAmount;
        if (colaborator.getAdditionalPayment()) {
            maxAllowedAmount = grossSalary * 0.6;
        } else {
            maxAllowedAmount = grossSalary * 0.5;
        }

        if (advance.getRequestAdAmount() > maxAllowedAmount) {
            throw new IllegalArgumentException("El monto solicitado excede el límite permitido.");
        }

        return repository.save(advance);
    }
}
