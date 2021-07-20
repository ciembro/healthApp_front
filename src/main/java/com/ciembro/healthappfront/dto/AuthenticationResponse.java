package com.ciembro.healthappfront.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("jwt")
    private String token;

    @JsonProperty("role")
    private String role;
}
