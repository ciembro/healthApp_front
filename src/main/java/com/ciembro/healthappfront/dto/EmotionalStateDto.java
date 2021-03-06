package com.ciembro.healthappfront.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionalStateDto {

    private long id;
    private String engText;
    private String plText;

    @Override
    public String toString() {
        return plText.toLowerCase();
    }
}