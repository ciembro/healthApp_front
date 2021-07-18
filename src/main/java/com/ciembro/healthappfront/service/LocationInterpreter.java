package com.ciembro.healthappfront.service;

import org.springframework.stereotype.Service;

public enum LocationInterpreter {

    INSTANCE;

    public String prepareQueryParam(String param){
        param = param.toLowerCase();
        return removePolishCharacters(param);
    }

    private String removePolishCharacters(String location){
        return location.replace("ą", "a")
                .replace("ć", "c")
                .replace("ę", "e")
                .replace("ł","l")
                .replace("ó", "o")
                .replace("ń", "n")
                .replace("ś", "s")
                .replace("ż", "z")
                .replace("ź", "z");
    }
}
