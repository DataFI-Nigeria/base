package org.lamisplus.base.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.lamisplus.base.model.Patient;
import org.lamisplus.base.model.Visit;
;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EncounterRequest implements Serializable{

    private Long id;
    @NotNull
    private Object formData;
    @NotNull
    private String formName;
    @NotNull
    private Long patientId;
    @NotNull
    private Long visitId;
    @NotNull
    private String serviceName;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDate encounterDate;

    @JsonIgnore
    private Patient patientByPatientId;

    @JsonIgnore
    private Visit visitByVisitId;
}
