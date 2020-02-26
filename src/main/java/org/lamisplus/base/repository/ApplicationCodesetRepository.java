package org.lamisplus.base.repository;

import org.lamisplus.base.model.ApplicationCodeset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationCodesetRepository extends JpaRepository<ApplicationCodeset, Long> {
    List<ApplicationCodeset> findBycodesetGroup(String codeset_group);
}



