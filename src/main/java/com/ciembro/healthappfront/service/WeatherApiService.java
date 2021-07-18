package com.ciembro.healthappfront.service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum WeatherApiService {

    INSTANCE;

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1/weather";
    private LocationInterpreter locationInterpreter = LocationInterpreter.INSTANCE;

    public List<String> getLocations(String loc){
        try {
            loc = locationInterpreter.prepareQueryParam(loc);
            String[] response = restTemplate.getForObject(
                    BASE_URL + "/loc/" + loc, String[].class);

            return Optional.ofNullable(response)
                    .map(Arrays::asList)
                    .orElse(new ArrayList<>());
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}
