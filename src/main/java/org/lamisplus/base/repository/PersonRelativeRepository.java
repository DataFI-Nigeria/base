package org.lamisplus.base.repository;

import org.lamisplus.base.model.PersonRelative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRelativeRepository extends JpaRepository<PersonRelative, Long> {
}
