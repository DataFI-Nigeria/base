package org.lamisplus.base.service;

import lombok.extern.slf4j.Slf4j;

import org.lamisplus.base.model.Country;
import org.lamisplus.base.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
@Slf4j
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public Country update(@RequestBody Country country) {
        Country result = countryRepository.save(country);
        return countryRepository.save(result);
    }

}
