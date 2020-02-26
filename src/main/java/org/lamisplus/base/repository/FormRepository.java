package org.lamisplus.base.repository;

import org.lamisplus.base.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {
    Form findByName(String name);
}

