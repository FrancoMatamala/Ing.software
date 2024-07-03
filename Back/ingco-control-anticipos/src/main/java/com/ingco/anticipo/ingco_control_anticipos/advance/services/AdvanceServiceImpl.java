package com.ingco.anticipo.ingco_control_anticipos.advance.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ingco.anticipo.ingco_control_anticipos.authenticate.entities.User;
import com.ingco.anticipo.ingco_control_anticipos.authenticate.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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
        Optional<User> userOptional = userRepository.findByRut(advance.getRutCollaborador());
        Optional<Collaborator> colaboratorOptional = colaboratorRepository.findById(userOptional
                .orElseThrow(() -> new NullPointerException("El colaborador no existe"))
                .getId());
        Collaborator colaborator = colaboratorOptional.orElseThrow(() -> new NullPointerException("El colaborador no existe"));

        advance.setCollaborator(colaborator);

        int advanceCount = repository.countByRutCollaboratorAndCurrentMonth(colaborator.getUser().getRut());
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

        advance.setEstado(Advance.Status.EN_ESPERA);

        return repository.save(advance);
    }

    @Override
    public Advance update(Advance advance) {
        advance.setRutCollaborador(advance.getCollaborator().getUser().getRut());
        return repository.save(advance);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Advance> findByIdCollaborator(Long ColaboratorId) {
        return repository.findByRutCollaborator(ColaboratorId);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Advance> findByIdCollaboratorAndCurrentMonth(Long ColaboratorId) {
        return repository.findByRutCollaboratorAndCurrentMonth(ColaboratorId);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Advance> findByDirectlyBossId(Long bossId) {
        return repository.findByDirectlyBossId(bossId);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Advance> findByDirectlyBossIdAndCurrentMonth(Long id) {
        return repository.findByDirectlyBossIdAndCurrentMonth(id);
    }
}
