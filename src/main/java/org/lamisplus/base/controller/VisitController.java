package org.lamisplus.base.controller;

import org.lamisplus.base.model.Visit;
import org.lamisplus.base.model.dto.HeaderUtil;
import org.lamisplus.base.model.dto.VisitRequest;
import org.lamisplus.base.repository.VisitRepository;
import org.lamisplus.base.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("api/visits")
public class VisitController {

    private final VisitRepository visitRepository;
    private final VisitService visitService;

    public VisitController(VisitRepository visitRepository, VisitService visitService) {
        this.visitRepository = visitRepository;
        this.visitService = visitService;
    }

    @GetMapping
    public Iterable findAll() {
        return visitRepository.findAll();
    }

    @GetMapping("/{id}")
    public Visit findOne(@PathVariable Long id) {
        return visitRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Visit> save(@RequestBody Visit visit) throws URISyntaxException {
        Visit v = this.visitService.save(visit);
        return ResponseEntity.created(new URI("/api/visits/" + visit.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, String.valueOf(visit.getId()))).body(visit);
    }


    @PutMapping("/{id}")
    public Visit update(@RequestBody Visit visit, @PathVariable Long id) {
        if (visit.getId() != (id)) {
            throw new RecordIdMismatchException(id);
        }
        visitRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return visitRepository.save(visit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        visitRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        visitRepository.deleteById(id);
    }


}
