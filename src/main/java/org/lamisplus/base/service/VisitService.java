package org.lamisplus.base.service;

import lombok.extern.slf4j.Slf4j;
import org.lamisplus.base.domain.dto.*;
import org.lamisplus.base.domain.entities.*;
import org.lamisplus.base.model.ApplicationCodeset;
import org.lamisplus.base.model.Patient;
import org.lamisplus.base.model.Visit;
import org.lamisplus.base.repositories.*;
import org.lamisplus.base.repository.ApplicationCodesetRepository;
import org.lamisplus.base.repository.PatientRepository;
import org.lamisplus.base.repository.VisitRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@org.springframework.stereotype.Service
@Transactional
@Slf4j
public class VisitService {

    private final ApplicationCodesetRepository applicationCodesetRepository;
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private static final String ENTITY_NAME = "visit";

    public VisitService(ApplicationCodesetRepository applicationCodesetRepository, VisitRepository visitRepository, PatientRepository patientRepository) {
        this.applicationCodesetRepository = applicationCodesetRepository;
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
    }


    private static Object exist(Visit o) {
        throw new BadRequestAlertException("Visit Error", ENTITY_NAME, "id Already Exist");
    }

    public Visit save(Visit visit) {
        Optional<Visit> visit = this.visitRepository.findById(visit.getId());
        visit.map(VisitService::exist);
        log.info("SAVING... " + visit);

        ApplicationCodeset visitType = applicationCodesetRepository.getOne(visit.getByVisitTypeId());
        Patient patient = patientRepository.getOne(visit.getPatientId());
        visit.setVisitType(visitType);
        visit.setPatient(patient);

        this.visitRepository.save(v);

        return v;
    }
}
