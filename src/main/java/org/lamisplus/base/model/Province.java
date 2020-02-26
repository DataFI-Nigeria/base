package org.lamisplus.base.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Province {
    private Long id;
    private String name;
    private Long stateId;
    private Collection<PersonContact> personContactsById;
    private State stateByStateId;

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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "state_id", nullable = true, insertable = false, updatable = false)
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Province province = (Province) o;
        return Objects.equals(id, province.id) &&
                Objects.equals(name, province.name) &&
                Objects.equals(stateId, province.stateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stateId);
    }

    @OneToMany(mappedBy = "provinceByProvinceId")
    public Collection<PersonContact> getPersonContactsById() {
        return personContactsById;
    }

    public void setPersonContactsById(Collection<PersonContact> personContactsById) {
        this.personContactsById = personContactsById;
    }

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    public State getStateByStateId() {
        return stateByStateId;
    }

    public void setStateByStateId(State stateByStateId) {
        this.stateByStateId = stateByStateId;
    }
}
