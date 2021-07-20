package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.CreatedInsightsDto;
import com.ciembro.healthappfront.dto.InsightsDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public enum AdminService {

    INSTANCE;

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1/admin";
    private HeadersService headersService = HeadersService.INSTANCE;

    public void loadDrugsDataToDb(){
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<String> entity = new HttpEntity<>(headers);

                restTemplate.exchange(
                        BASE_URL + "/drugs",
                        HttpMethod.POST,
                        entity,
                        ResponseEntity.class);

            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadEmotionsToDb(){
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<String> entity = new HttpEntity<>(headers);

                restTemplate.exchange(
                        BASE_URL + "/emotions",
                        HttpMethod.POST,
                        entity,
                        ResponseEntity.class);

            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadSideEffectsToDb(){
        HttpHeaders headers = headersService.getHeaders();
        if (headers != null) {
            try {
                HttpEntity<String> entity = new HttpEntity<>(headers);

                restTemplate.exchange(
                        BASE_URL + "/effects",
                        HttpMethod.POST,
                        entity,
                        ResponseEntity.class);

            } catch (RestClientException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
