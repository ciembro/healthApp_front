package com.ciembro.healthappfront.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideEffectDto {

    private long id;
    private String username;
    private long drugId;
    private LocalDateTime creationDate;
    private String details;
}
