package com.ingco.anticipo.ingco_control_anticipos.advance.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.colaborator.entities.Colaborator;
import com.ingco.anticipo.ingco_control_anticipos.colaborator.repositories.ColaboratorRepository;
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
    private ColaboratorRepository colaboratorRepository;

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
        Calendar today = Calendar.getInstance();
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        if (currentDay < 1 || currentDay > 10) {
            throw new IllegalArgumentException("Los anticipos solo pueden ser solicitados entre el 1 y el 10 de cada mes.");
        }

        Optional<Colaborator> colaboratorOptional = colaboratorRepository.findById(advance.getIdColaborator());
        Colaborator colaborator = colaboratorOptional.orElseThrow(() -> new IllegalArgumentException("El colaborador no existe"));

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

        if (advance.getRequestedamount() > maxAllowedAmount) {
            throw new IllegalArgumentException("El monto solicitado excede el límite permitido.");
        }

        return repository.save(advance);

        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(dateEntry);
        // calendar.add(Calendar.YEAR, 5);
        // Date fiveYearsAfterEntry = calendar.getTime();
        // additionalPayment = new Date().after(fiveYearsAfterEntry);
    }
}
