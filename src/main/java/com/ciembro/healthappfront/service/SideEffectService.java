package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.SideEffectDto;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum SideEffectService {

    INSTANCE;

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1/effects";
    private HeadersService headersService = HeadersService.INSTANCE;

    public List<SideEffectDto> getSideEffectList(){
        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<SideEffectDto[]> response = restTemplate.exchange(
                        BASE_URL,
                        HttpMethod.GET,
                        entity,
                        SideEffectDto[].class);

                SideEffectDto[] responseBody = response.getBody();
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
