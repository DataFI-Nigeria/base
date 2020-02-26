package org.lamisplus.base.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VisitRequest {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDate date_visit_end;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDate date_visit_start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "KK:mm a")
    private LocalTime time_visit_end;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "KK:mm a")
    private LocalTime time_visit_start;

    private Long patientId;
    private Long visit_type_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDate date_next_appointment;

}

