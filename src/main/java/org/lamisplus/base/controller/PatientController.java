package org.lamisplus.base.controller;


import org.lamisplus.base.model.Patient;
import org.lamisplus.base.model.Person;
import org.lamisplus.base.model.dto.HeaderUtil;
import org.lamisplus.base.model.dto.PatientRequest;
import org.lamisplus.base.repository.*;
import org.lamisplus.base.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PersonRepository personRepository;
    private final PersonContactRepository personContactRepository;
    private final PersonRelativeRepository personRelativeRepository;
    private final PatientRepository patientRepository;
    private final PatientService patientService;
    private final ServiceEnrollmentRepository patientServiceEnrollmentRepository;

    public PatientController(PersonRepository personRepository, PatientService patientService, PersonContactRepository personContactRepository, PersonRelativeRepository personRelativeRepository, PatientRepository patientRepository, ServiceEnrollmentRepository patientServiceEnrollmentRepository) {
        this.personRepository = personRepository;
        this.patientService = patientService;
        this.personContactRepository = personContactRepository;
        this.personRelativeRepository = personRelativeRepository;
        this.patientRepository = patientRepository;
        this.patientServiceEnrollmentRepository = patientServiceEnrollmentRepository;
    }


    @GetMapping
    public Iterable findAll() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient findOne(@PathVariable Long id) {
        return patientRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> save(@RequestBody PatientRequest patientRequest) throws URISyntaxException {
        Person person = this.patientService.save(patientRequest);
        return ResponseEntity.created(new URI("/api/patient/" + person.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(person.getId()))).body(person);
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient save(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }
    */

    @PutMapping("/{id}")
    public Patient update(@RequestBody Patient patient, @PathVariable Long id) {
        if (patient.getId() != (id)) {
            throw new RecordIdMismatchException(id);
        }
        patientRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return patientRepository.save(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        patientRepository.deleteById(id);
    }
}
