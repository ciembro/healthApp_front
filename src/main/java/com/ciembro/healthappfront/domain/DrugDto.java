package com.ciembro.healthappfront.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
