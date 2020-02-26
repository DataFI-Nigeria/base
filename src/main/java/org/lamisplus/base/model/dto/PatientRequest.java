package org.lamisplus.base.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PatientRequest {
    private Long id;
    @NotNull
    private LocalDate dateRegistration;
    @NotNull
    private String hospitalNumber;
    @NotNull
    private Long facilityId;
    private PersonRequest person;

}
