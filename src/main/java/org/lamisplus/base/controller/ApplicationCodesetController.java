package org.lamisplus.base.controller;

import org.lamisplus.base.model.ApplicationCodeset;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/applicationcodesets")
public class ApplicationCodesetController<ApplicationCodeRepository, ApplicationCodesRepository> {
    private final ApplicationCodesRepository applicationCodesRepository;

    public ApplicationCodesetController(ApplicationCodesRepository applicationCodesRepository) {
        this.applicationCodesRepository = applicationCodesRepository;
    }

    @GetMapping
    public List<ApplicationCodeset> findAll() {
        return applicationCodesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ApplicationCodeset> findOne(@PathVariable Long id) {
        return applicationCodesRepository.findById(id);
    }

    @GetMapping("/{codeGroup}")
    public List<ApplicationCodeset> findByCodeGroup(@PathVariable String codeGroup) {
        return applicationCodesRepository.findByCodeGroup(codeGroup);
    }

}
