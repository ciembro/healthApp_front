package com.ciembro.healthappfront.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtToken {

    @JsonProperty("jwt")
    private String token;
}
