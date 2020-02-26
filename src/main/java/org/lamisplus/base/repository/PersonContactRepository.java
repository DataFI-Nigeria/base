package org.lamisplus.base.repository;

import org.lamisplus.base.model.Person;
import org.lamisplus.base.model.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactRepository extends JpaRepository<PersonContact,Long> {
    PersonContact findBypersonByPersonId(Person person);
}
