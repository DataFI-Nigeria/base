package org.lamisplus.base.repository;

import org.lamisplus.base.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository

//EncounterRepository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    //Encounter
    Optional<Encounter> findByPatientIdAndServiceNameAndFormNameAndVisitId(Long patientId, String serviceName, String formName, Long visitId);
    Optional<Encounter> findByPatientIdAndServiceNameAndFormNameAndEncounterDate(Long patientId, String serviceName, String formName, LocalDate encounterDate);
    //List<Encounter> findBypatientByPatientId(Patient patient);
    //List<PatientObservation> findByPatientAndFormCodeTitle(Patient patient, Long formCode, String title);
}

