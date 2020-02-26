package org.lamisplus.base.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person_contact", schema = "public", catalog = "lamisplus")
public class PersonContact {
    private Long id;
    private String mobilePhoneNumber;
    private String alternatePhoneNumber;
    private String email;
    private String zipCode;
    private String city;
    private String street;
    private String landmark;
    private Long countryId;
    private Long stateId;
    private Long provinceId;
    private Long personId;
    private Country countryByCountryId;
    private State stateByStateId;
    private Province provinceByProvinceId;
    private Person personByPersonId;

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
    @Column(name = "mobile_phone_number", nullable = true, length = -1)
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    @Basic
    @Column(name = "alternate__phone_number", nullable = true, length = -1)
    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "zip_code", nullable = true, length = -1)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "city", nullable = true, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = true, length = -1)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "landmark", nullable = true, length = -1)
    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Basic
    @Column(name = "country_id", nullable = false, insertable = false, updatable = false)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "state_id", nullable = false)
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "province_id", nullable = false)
    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "person_id", nullable = false, insertable = false, updatable = false)
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonContact that = (PersonContact) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) &&
                Objects.equals(alternatePhoneNumber, that.alternatePhoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(landmark, that.landmark) &&
                Objects.equals(countryId, that.countryId) &&
                Objects.equals(stateId, that.stateId) &&
                Objects.equals(provinceId, that.provinceId) &&
                Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobilePhoneNumber, alternatePhoneNumber, email, zipCode, city, street, landmark, countryId, stateId, provinceId, personId);
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false, insertable = true, updatable = false)
    public Country getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public State getStateByStateId() {
        return stateByStateId;
    }

    public void setStateByStateId(State stateByStateId) {
        this.stateByStateId = stateByStateId;
    }

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Province getProvinceByProvinceId() {
        return provinceByProvinceId;
    }

    public void setProvinceByProvinceId(Province provinceByProvinceId) {
        this.provinceByProvinceId = provinceByProvinceId;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false, insertable = true, updatable = false)
    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
