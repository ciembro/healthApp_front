package com.ciembro.healthappfront.extrasuper.service;

import java.util.Arrays;
import java.util.List;

public class WeatherApiService {


    public List<String> getLocations(String loc){
        return Arrays.asList("Poznań", "Kraków", "Warszawa");
    }
}
