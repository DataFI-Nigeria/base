package org.lamisplus.base.model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Person {
    private Long id;
    private LocalDate dob;
    private Boolean dobEstimated;
    private String firstName;
    private String lastName;
    private String otherNames;
    private Long educationId;
    private Long genderId;
    private Long occupationId;
    private Long maritalStatusId;
    private Long personTitleId;
    private Collection<Patient> patientsById;
    private Collection<PersonContact> personContactsById;
    private Collection<PersonRelative> personRelativesById;
    private Collection<User> usersById;

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
    @Column(name = "dob", nullable = true)
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Basic
    @Column(name = "dob_estimated", nullable = true)
    public Boolean getDobEstimated() {
        return dobEstimated;
    }

    public void setDobEstimated(Boolean dobEstimated) {
        this.dobEstimated = dobEstimated;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "other_names", nullable = true, length = 255)
    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    @Basic
    @Column(name = "education_id", nullable = true)
    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    @Basic
    @Column(name = "gender_id", nullable = true)
    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    @Basic
    @Column(name = "occupation_id", nullable = true)
    public Long getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Long occupationId) {
        this.occupationId = occupationId;
    }

    @Basic
    @Column(name = "marital_status_id", nullable = true)
    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    @Basic
    @Column(name = "person_title_id", nullable = true)
    public Long getPersonTitleId() {
        return personTitleId;
    }

    public void setPersonTitleId(Long personTitleId) {
        this.personTitleId = personTitleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(dob, person.dob) &&
                Objects.equals(dobEstimated, person.dobEstimated) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(otherNames, person.otherNames) &&
                Objects.equals(educationId, person.educationId) &&
                Objects.equals(genderId, person.genderId) &&
                Objects.equals(occupationId, person.occupationId) &&
                Objects.equals(maritalStatusId, person.maritalStatusId) &&
                Objects.equals(personTitleId, person.personTitleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dob, dobEstimated, firstName, lastName, otherNames, educationId, genderId, occupationId, maritalStatusId, personTitleId);
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<Patient> getPatientsById() {
        return patientsById;
    }

    public void setPatientsById(Collection<Patient> patientsById) {
        this.patientsById = patientsById;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<PersonContact> getPersonContactsById() {
        return personContactsById;
    }

    public void setPersonContactsById(Collection<PersonContact> personContactsById) {
        this.personContactsById = personContactsById;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<PersonRelative> getPersonRelativesById() {
        return personRelativesById;
    }

    public void setPersonRelativesById(Collection<PersonRelative> personRelativesById) {
        this.personRelativesById = personRelativesById;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
