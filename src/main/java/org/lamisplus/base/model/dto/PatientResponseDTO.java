package org.lamisplus.base.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponseDTO {
    private Long id;
    private LocalDate dateRegistration;
    private Long facilityId;
    private String hospitalNumber;
    private PersonResponse person;
}
