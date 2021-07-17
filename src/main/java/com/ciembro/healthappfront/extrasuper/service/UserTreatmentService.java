package com.ciembro.healthappfront.extrasuper.service;

import com.ciembro.healthappfront.extrasuper.dto.CreatedUserTreatmentDto;
import com.ciembro.healthappfront.extrasuper.dto.DrugDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserTreatmentService {

    public List<CreatedUserTreatmentDto> getExampleData(){
        List<CreatedUserTreatmentDto> treatmentDtos = new ArrayList<>();
        DrugDto drugDto =  new DrugDto(1L,
                "common",
                "trade",
                "dose",
                "brand",
                "subst",
                "http//leaflet.com");
        CreatedUserTreatmentDto dto = new CreatedUserTreatmentDto();
        dto.setId(18L);
        dto.setStartedAt(LocalDate.now());
        dto.setDrugDto(drugDto);

        dto.setUsername("user");
        treatmentDtos.add(dto);
        return treatmentDtos;
    }
}
