package org.lamisplus.base.repository;

import org.lamisplus.base.model.Country;
import org.lamisplus.base.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findBycountryByCountryId(Country country);
}
