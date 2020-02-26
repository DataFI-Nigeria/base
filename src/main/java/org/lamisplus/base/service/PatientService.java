package org.lamisplus.base.service;

import lombok.extern.slf4j.Slf4j;

import org.lamisplus.base.model.*;
import org.lamisplus.base.model.dto.DtoToEntity;
import org.lamisplus.base.model.dto.PatientRequest;
import org.lamisplus.base.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
@Transactional
@Slf4j
public class PatientService {

    // Declaring variable used in the PatientServices
    private static final String ENTITY_NAME = "patient";
    private final ApplicationCodesetRepository AppCodesetRepository;
    private final PatientRepository patientRepository;
    private final PersonRepository personRepository;
    private final ServiceRepository serviceRepository;
    private final IdentifierTypeRepository identifierTypeRepository;
    private final ServiceEnrollmentRepository serviceEnrollmentRepository;
    private final PersonContactRepository personContactsRepository;
    private final PersonRelativeRepository personRelativeRepository;
    private final ModuleRepository moduleRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final ProvinceRepository provinceRepository;


    public PatientService(PatientRepository patientRepository, PersonRepository personRepository, ServiceRepository serviceRepository, IdentifierTypeRepository identifierTypeRepository, ServiceEnrollmentRepository patientServiceEnrollmentRepository, PersonContactRepository personContactsRepository, PersonRelativeRepository personRelativeRepository, ModuleRepository moduleRepository, ApplicationCodesetRepository AppCodesetRepository, CountriesRepository countriesRepository, CountryRepository countryRepository, StateRepository stateRepository, ProvinceRepository provinceRepository) {
        this.patientRepository = patientRepository;
        this.personRepository = personRepository;
        this.serviceRepository = serviceRepository;
        this.identifierTypeRepository = identifierTypeRepository;
        this.serviceEnrollmentRepository = patientServiceEnrollmentRepository;
        this.personContactsRepository = personContactsRepository;
        this.personRelativeRepository = personRelativeRepository;
        this.moduleRepository = moduleRepository;
        this.AppCodesetRepository = AppCodesetRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.provinceRepository = provinceRepository;
    }

    private static Object exist(Patient o) {
        throw new BadRequestAlertException("Patient Already Exist", ENTITY_NAME, "id Already Exist");
    }

    private static Patient notExit() {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "id is null");
    }

    /** Saving a patient */
    public Person save(PatientRequest patientRequest) {
        //Creating a patient object
        Optional<Patient> patient1 = this.patientRepository.findById(patientRequest.getId());
        patient1.map(PatientService::exist);
        log.info("SAVING... " + patientRequest);

        //Creating a person object
        Person person = new Person();

        //Setting a person
        person.setFirstName(patientRequest.getPerson().getFirstName());
        person.setLastName(patientRequest.getPerson().getLastName());
        person.setOtherNames(patientRequest.getPerson().getOtherNames());
        person.setDob(patientRequest.getPerson().getDob());
        person.setDobEstimated(patientRequest.getPerson().getDobEstimated());
        person.setPersonTitleId(patientRequest.getPerson().getTitleId());

        //Converting the related details like gender, education & occupation to Application Codeset
        ApplicationCodeset maritalStatus = AppCodesetRepository.getOne(patientRequest.getPerson().getMaritalStatusId());
        person.setMaritalStatusId(maritalStatus.getId());
        ApplicationCodeset gender = AppCodesetRepository.getOne(patientRequest.getPerson().getGenderId());
        person.setGenderId(gender.getId());
        ApplicationCodeset education = AppCodesetRepository.getOne(patientRequest.getPerson().getEducationId());
        person.setEducationId(education.getId());
        ApplicationCodeset occupation = AppCodesetRepository.getOne(patientRequest.getPerson().getOccupationId());
        person.setOccupationId(occupation.getId());
        //Persisting the Person
        Person person1 = this.personRepository.save(person);

        //creating a patient object
        Patient p = new Patient();
        //Setters for patient
        p.setPersonByPersonId(person1);
        p.setDateRegistration(patientRequest.getDateRegistration());
        p.setFacilityId(patientRequest.getFacilityId());
        p.setHospitalNumber(patientRequest.getHospitalNumber());

        //Persisting the patient
        this.patientRepository.save(p);

        //service object
       /*
        Service service = this.serviceRepository.findByServiceName("GEN_SERVICE");
        if (service == null){
            Module module = new Module();
            module.setName("PATIENT");
            Module module1 = moduleRepository.save(module);
            Service setService = new Service();
            setService.setServiceName("GEN_SERVICE");
            setService.setServiceName(module1.getName());
            this.serviceRepository.save(setService);
        }
        *
        */

        /*IdentifierType identifierType = this.identifierTypeRepository.findByIdentifierTypeName("HOSPITAL_NUMBER");
        if (identifierType == null){
            IdentifierType identifierType1 = new IdentifierType();
            // just a test (Remove later)
            identifierType1.setId(10L);
            identifierType1.setIdentifierTypeName("HOSPITAL_NUMBER");
            identifierType1.setValidator("");
            this.identifierTypeRepository.save(identifierType1);
        }
        *
        */
        /* Do this Later

        Service service3 = serviceRepository.findByServiceName("GEN_SERVICE");
        IdentifierType identifierType4 = identifierTypeRepository.findByIdentifierTypeName("HOSPITAL_NUMBER");
        ServiceEnrollment serviceEnrollment = new ServiceEnrollment();
        serviceEnrollment.setDateEnrollment(patientRequest.getDateRegistration());
        serviceEnrollment.setIdentifierTypeByIdentifierTypeId(identifierType4);
        serviceEnrollment.setIdentifierValue(patientRequest.getHospitalNumber());
        serviceEnrollment.setPatientByPatientId(patient2);
        serviceEnrollment.setServiceByServicesId(service3);
        this.serviceEnrollmentRepository.save(serviceEnrollment);
*/
        Country country = countryRepository.getOne(patientRequest.getPerson().getPersonContact().getCountryId());
        State state = stateRepository.getOne(patientRequest.getPerson().getPersonContact().getStateId());
        Province province = provinceRepository.getOne(patientRequest.getPerson().getPersonContact().getProvinceId());
        //Persisting PersonContact Object
        PersonContact personContact = DtoToEntity.convertPersonContactDTOToPersonContact(patientRequest.getPerson().getPersonContact());
        personContact.setCountryByCountryId(country);
        personContact.setStateId(state.getId());
        personContact.setProvinceId(province.getId());
        personContact.setCountryId(country.getId());
        personContact.setPersonByPersonId(person1);

        this.personContactsRepository.save(personContact);

        //Creating Relatives Objects e.g. Can be more than one relatives
        //and persistent them
        List<PersonRelative> personRelatives = patientRequest.getPerson().getPersonRelative();

            personRelatives.stream().map(DtoToEntity::convertPersonRelativeDTORelative).forEach(personRelative -> {
                personRelative.setPersonByPersonId(person1);
                if(personRelative.getFirstName().equals(""))
                this.personRelativeRepository.save(personRelative);
            });

        return person;
    }



}
