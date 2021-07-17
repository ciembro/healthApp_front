package com.ciembro.healthappfront.extrasuper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToRegisterDto {

    private String username;
    private String location;
    private String email;
    private String password;
}
