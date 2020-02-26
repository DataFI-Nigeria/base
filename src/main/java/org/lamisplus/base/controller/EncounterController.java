package org.lamisplus.base.controller;

import org.lamisplus.base.repository.EncounterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.lamisplus.base.model.Encounter;

import java.util.List;

@RestController
@RequestMapping("api/encounters")
public class EncounterController {

    private final EncounterRepository encounterRepository;

    public EncounterController(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    @GetMapping
    public List<Encounter> findAll() {
        return encounterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Encounter findOne(@PathVariable Long id) {
        return encounterRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Encounter save(@RequestBody Encounter encounter) {
        return encounterRepository.save(encounter);
    }

    @PutMapping("/{id}")
    public Encounter update(@RequestBody Encounter encounter, @PathVariable Long id) {
        if (!encounter.getId().equals(id)) {
            throw new RecordIdMismatchException(id);
        }
        encounterRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return encounterRepository.save(encounter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        encounterRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        encounterRepository.deleteById(id);
    }
}
