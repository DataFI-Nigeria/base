package org.lamisplus.base.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Patient {
    private Long id;
    private LocalDate dateRegistration;
    private Long facilityId;
    private Long personId;
    private String hospitalNumber;
    private Facility facilityByFacilityId;
    private Person personByPersonId;
    private Collection<ServiceEnrollment> serviceEnrollmentsById;
    private Collection<Visit> visitsById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_registration", nullable = false)
    public LocalDate getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(LocalDate dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Basic
    @Column(name = "facility_id", nullable = true, insertable = false, updatable = false)
    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    @Basic
    @Column(name = "person_id", nullable = false, insertable = false, updatable = false)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "hospital_number", nullable = true, length = -1)
    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) &&
                Objects.equals(dateRegistration, patient.dateRegistration) &&
                Objects.equals(facilityId, patient.facilityId) &&
                Objects.equals(personId, patient.personId) &&
                Objects.equals(hospitalNumber, patient.hospitalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateRegistration, facilityId, personId, hospitalNumber);
    }

    /*
    @OneToMany(mappedBy = "patientByPatientId")
    public Collection<Encounter> getEncountersById() {
        return encountersById;
    }

    public void setEncountersById(Collection<Encounter> encountersById) {
        this.encountersById = encountersById;
    }
    */
    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    public Facility getFacilityByFacilityId() {
        return facilityByFacilityId;
    }

    public void setFacilityByFacilityId(Facility facilityByFacilityId) {
        this.facilityByFacilityId = facilityByFacilityId;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    @OneToMany(mappedBy = "patientByPatientId")
    public Collection<ServiceEnrollment> getServiceEnrollmentsById() {
        return serviceEnrollmentsById;
    }

    public void setServiceEnrollmentsById(Collection<ServiceEnrollment> serviceEnrollmentsById) {
        this.serviceEnrollmentsById = serviceEnrollmentsById;
    }
}
