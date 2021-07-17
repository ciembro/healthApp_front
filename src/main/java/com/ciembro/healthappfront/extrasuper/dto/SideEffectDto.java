package com.ciembro.healthappfront.extrasuper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideEffectDto {

    private long id;
    private String text;

    @Override
    public String toString() {
        return text;
    }
}
