package com.ciembro.healthappfront.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugDto {

    private long id;
    private int uniqueDrugId;
    private String internationalName;
    private String tradeName;
    private String dosage;
    private String brand;
    private String activeSubstance;
    private String leafletUrl;


}
