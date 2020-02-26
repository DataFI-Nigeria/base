package org.lamisplus.base.service;

import lombok.extern.slf4j.Slf4j;
import org.lamisplus.base.model.*;
import org.lamisplus.base.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
@Slf4j
public class EncounterService {
    private static final String ENTITY_NAME = "encounter";
    private final PatientRepository patientRepository;
    private final ServiceRepository serviceRepository;
    private final EncounterRepository encounterRepository;
    private final VisitRepository visitRepository;
    private final FormRepository formRepository;
    private final EncounterRepository patientEncounterRepository;


    public EncounterService(PatientRepository patientRepository, ServiceRepository serviceRepository, EncounterRepository encounterRepository, VisitRepository visitRepository, FormRepository formRepository, EncounterRepository patientEncounterRepository) {
        this.patientRepository = patientRepository;
        this.serviceRepository = serviceRepository;
        this.encounterRepository = encounterRepository;
        this.visitRepository = visitRepository;
        this.formRepository = formRepository;
        this.patientEncounterRepository = patientEncounterRepository;
    }

    public Encounter save(EncounterRequest encounterRequest) {
        //log.debug("REST: {}", encounterRequest);
        //Checking if a patient by patient Id exist
        Encounter e = new Encounter();
        try {
            Patient patient1 = this.patientRepository.getOne(encounterRequest.getPatientId());
            //Check if a form by form name exist
            Form form = formRepository.findByName(encounterRequest.getFormName());
            //Checking if service name exist
            Service service = this.serviceRepository.findByServiceName(encounterRequest.getServiceName());
            //Checking is the patient has been checked in by visit id
            Visit visit = this.visitRepository.getOne(encounterRequest.getVisitId());
            //check if an encounter already exist with
            //findBy PatientId And ServiceName And FormName And VisitId
            Optional<Encounter> patientEncounter = this.encounterRepository.findByPatientIdAndServiceNameAndFormNameAndVisitId(patient1.getId(), service.getServiceName(), encounterRequest.getFormName(), visit.getId());
            patientEncounter.map(EncounterService::exist);
            log.info("SAVING1... " + encounterRequest);


            Encounter patientEncounter1 = new Encounter();
            log.info("SAVING12... " + encounterRequest);
            //issues may come up with wrong form name
            patientEncounter1.setFormName(form.getName());
            patientEncounter1.setFormData(encounterRequest.getFormData());
            //
            patientEncounter1.setEncounterDate(encounterRequest.getEncounterDate());
            patientEncounter1.setPatientId(patient1.getId());
            patientEncounter1.setVisitId(visit.getId());
            patientEncounter1.setServiceName(service.getServiceName());
             e = this.encounterRepository.save(patientEncounter1);
        }catch(Exception ex){
            log.error(ex.getMessage(), ex);
            throw ex;
        }
        return e;

    }

    //Getting a single encounter for a patient
    public Encounter singleEncounter(Long patientId, String formName, String serviceName, LocalDate date){
        Optional<Encounter> patientEncounter = this.patientEncounterRepository.findByPatientIdAndServiceNameAndFormNameAndEncounterDate(
                patientId, formName, serviceName, date);

        log.info("Patient ID is ",patientId);

        Encounter patientEncounter1 = patientEncounter.get();

        return patientEncounter1;

    }



}
