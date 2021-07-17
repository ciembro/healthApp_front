package com.ciembro.healthappfront.extrasuper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class DrugDto {

    private long id;
    private String commonName;
    private String tradeName;
    private String dose;
    private String brand;
    private String activeSubstance;
    private String leafletUrl;

}
