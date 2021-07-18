package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

public enum InsightsService {

    INSTANCE;

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1/insights";
    private HeadersService headersService = HeadersService.INSTANCE;

    public CreatedInsightsDto createInsights(InsightsDto insightsDto) {
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<InsightsDto> entity = new HttpEntity<>(insightsDto, headers);

                ResponseEntity<CreatedInsightsDto> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.POST,
                        entity,
                        CreatedInsightsDto.class);

                return response.getBody();
            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;

    }
    public CreatedInsightsDto updateInsights(CreatedInsightsDto insightsDto){
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<CreatedInsightsDto> entity = new HttpEntity<>(insightsDto, headers);

                ResponseEntity<CreatedInsightsDto> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.PUT,
                        entity,
                        CreatedInsightsDto.class);

                return response.getBody();
            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public void deleteInsights(CreatedInsightsDto insightsDto){
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<CreatedInsightsDto> entity = new HttpEntity<>(insightsDto, headers);

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

    public List<CreatedInsightsDto> getAllUserInsights() {
        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<CreatedInsightsDto[]> response = restTemplate.exchange(
                        BASE_URL + "/all",
                        HttpMethod.GET,
                        entity,
                        CreatedInsightsDto[].class);

                CreatedInsightsDto[] responseBody = response.getBody();
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

