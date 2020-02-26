package org.lamisplus.base.repository;

import org.lamisplus.base.model.Patient;
import org.lamisplus.base.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select p from Person p where lower(p.firstName) like lower(concat('%', :search, '%')) " +
            "or lower(p.lastName) like lower(concat('%', :search, '%'))")
    List<Person> findByFirstNameLastName(String search);

}
