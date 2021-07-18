package com.ciembro.healthappfront.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreatedUserTreatmentDto {

    private long id;
    private String username;
    private DrugDto drugDto;
    private LocalDate startedAt;
    private LocalDate finishedAt;
}
