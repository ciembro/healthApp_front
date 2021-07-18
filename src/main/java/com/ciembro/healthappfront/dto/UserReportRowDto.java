package com.ciembro.healthappfront.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserReportRowDto {

    private CreatedInsightsDto insights;
    private List<DrugDto> drugs;
}
