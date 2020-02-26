package org.lamisplus.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.lamisplus.base.domain.dto.BadRequestAlertException;
import org.lamisplus.base.domain.dto.HeaderUtil;
import org.lamisplus.base.domain.entities.Country;
import org.lamisplus.base.model.Country;
import org.lamisplus.base.repositories.CountriesRepository;
import org.lamisplus.base.repository.CountryRepository;
import org.lamisplus.base.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@Slf4j
public class CountryController {

    private final CountryRepository countryRepository;
    private final CountryService countryService;
    private static final String ENTITY_NAME = "country";

    public CountryController(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Country> save(@RequestBody Country country) throws URISyntaxException {
        Optional<Country> patient1 = countryRepository.findById(country.getId());
        patient1.map(CountryController::exist);
        Country result = countryService.save(country);
        return ResponseEntity.created(new URI("/api/country/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId()))).body(result);
    }

    @PutMapping
    public ResponseEntity<Country> update(@RequestBody Country country) throws URISyntaxException {
        Optional<Country> country1 = countryRepository.findById(country.getId());
        country1.orElseGet(CountryController::notExit);
        Country result = countryService.update(country);
        return ResponseEntity.created(new URI("/api/country/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(result.getId())))
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountry() {
        log.info("Tested now");
        return ResponseEntity.ok(countryRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getSingle(@PathVariable Long id) {
        Optional<Country> country = this.countryRepository.findById(id);
        if (country.isPresent()){
            Country countryArchive = country.get();
            return ResponseEntity.ok(countryArchive);
        } else throw new BadRequestAlertException("Record not found with id ", ENTITY_NAME, "id is  Null");

    }


}
