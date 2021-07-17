package com.ciembro.healthappfront.extrasuper.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatedInsightsDto {

    private long id;
    private WeatherConditionsDto weather;
    private String username;
    private LocalDate creationDate;
    private Set<EmotionalStateDto> emotions;
    private Set<SideEffectDto> sideEffects;
    private String comment;
}
