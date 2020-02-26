package org.lamisplus.base.controller;

import lombok.extern.slf4j.Slf4j;

import org.lamisplus.base.model.Encounter;
import org.lamisplus.base.model.dto.HeaderUtil;
import org.lamisplus.base.repository.EncounterRepository;
import org.lamisplus.base.repository.PatientRepository;
import org.lamisplus.base.service.EncounterService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/encounter")
@Slf4j
public class PatientEncounterController {
    private static String ENTITY_NAME = "encounter";
    private final EncounterRepository patientEncounterRepository;
    private final EncounterService encounterService;
    private final PatientRepository patientRepository;


    public PatientEncounterController(PatientRepository patientRepository, EncounterRepository patientEncounterRepository, EncounterService encounterService) {
        this.patientRepository = patientRepository;
        this.patientEncounterRepository = patientEncounterRepository;
        this.encounterService = encounterService;
    }

    @PostMapping
    public ResponseEntity<Encounter> save(@RequestBody EncounterRequest patientEncounter) throws URISyntaxException {
        Encounter encounter = encounterService.save(patientEncounter);
        return ResponseEntity.created(new URI("/api/encounter/" + encounter.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(encounter.getId()))).body(encounter);
    }

    @PutMapping
    public ResponseEntity<Encounter> update(@RequestBody Encounter patientEncounter) throws URISyntaxException {
        Optional<Encounter> patient1 = this.patientEncounterRepository.findById(patientEncounter.getId());
        patient1.orElseGet(PatientEncounterController::notExit);
        Encounter result = patientEncounterRepository.save(patientEncounter);
        return ResponseEntity.created(new URI("/api/encounter/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId()))).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity getAllEncounter() {
        return ResponseEntity.ok(patientEncounterRepository.findAll());
    }


    @GetMapping("/single_encounter")
    @ResponseBody
    public ResponseEntity getSingle(@RequestParam("patientId") Long patientId, @RequestParam("formName")
            String formName, @RequestParam("serviceName") String serviceName,
                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        log.info("patientId is ", patientId);
        Encounter encounter = encounterService.singleEncounter(patientId, formName, serviceName, localDate);
        return ResponseEntity.ok(encounter);
    }

}
