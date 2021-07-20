package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.DrugDto;
import com.ciembro.healthappfront.dto.UserReportRowDto;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum UserReportService {

    INSTANCE;

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1/report";
    private HeadersService headersService = HeadersService.INSTANCE;


    public List<UserReportRowDto> generateReport(){
        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<UserReportRowDto[]> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.GET,
                        entity,
                        UserReportRowDto[].class);

                UserReportRowDto[] responseBody = response.getBody();
                return Optional.ofNullable(responseBody)
                        .map(Arrays::asList)
                        .orElse(new ArrayList<>());
            }
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<UserReportRowDto> filterByDrug(long drugId){
        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<UserReportRowDto[]> response = restTemplate.exchange(
                        BASE_URL + "/drug/" + drugId,
                        HttpMethod.GET,
                        entity,
                        UserReportRowDto[].class);

                UserReportRowDto[] responseBody = response.getBody();
                return Optional.ofNullable(responseBody)
                        .map(Arrays::asList)
                        .orElse(new ArrayList<>());
            }
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}

