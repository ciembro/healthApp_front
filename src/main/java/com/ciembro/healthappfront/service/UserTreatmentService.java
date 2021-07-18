package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.CreatedUserTreatmentDto;
import com.ciembro.healthappfront.dto.UserTreatmentDto;
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

public enum UserTreatmentService {

    INSTANCE;

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1/treatment";
    private HeadersService headersService = HeadersService.INSTANCE;

    public CreatedUserTreatmentDto createUserTreatment(UserTreatmentDto treatmentDto) {
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<UserTreatmentDto> entity = new HttpEntity<>(treatmentDto, headers);

                ResponseEntity<CreatedUserTreatmentDto> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.POST,
                        entity,
                        CreatedUserTreatmentDto.class);

                return response.getBody();
            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public CreatedUserTreatmentDto updateUserTreatment(CreatedUserTreatmentDto treatmentDto) {
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<CreatedUserTreatmentDto> entity = new HttpEntity<>(treatmentDto, headers);

                ResponseEntity<CreatedUserTreatmentDto> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.PUT,
                        entity,
                        CreatedUserTreatmentDto.class);

                return response.getBody();
            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void deleteUserTreatment(CreatedUserTreatmentDto treatmentDto) {
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<CreatedUserTreatmentDto> entity = new HttpEntity<>(treatmentDto, headers);

                restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.DELETE,
                        entity,
                        CreatedUserTreatmentDto.class);
            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<CreatedUserTreatmentDto> getAllUserTreatments() {

        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<CreatedUserTreatmentDto[]> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.GET,
                        entity,
                        CreatedUserTreatmentDto[].class);

                CreatedUserTreatmentDto[] responseBody = response.getBody();
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
