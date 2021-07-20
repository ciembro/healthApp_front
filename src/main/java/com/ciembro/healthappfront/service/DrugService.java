package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.DrugDto;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;


public class DrugService {

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1";
    private final HeadersService headersService = HeadersService.INSTANCE;

    public List<DrugDto> searchMatchingDrugs(String text){

        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<DrugDto[]> response = restTemplate.exchange(
                        BASE_URL + "/drugs/search/" + text,
                        HttpMethod.GET,
                        entity,
                        DrugDto[].class);

                DrugDto[] responseBody = response.getBody();
                return Optional.ofNullable(responseBody)
                        .map(Arrays::asList)
                        .orElse(new ArrayList<>());
            }
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

        public List<DrugDto> getDrugsByUser(){
            try {
                HttpHeaders headers = headersService.getHeaders();
                if (headers != null){
                    HttpEntity<String> entity = new HttpEntity<>(headers);

                    ResponseEntity<DrugDto[]> response = restTemplate.exchange(
                            BASE_URL + "/drugs/user",
                            HttpMethod.GET,
                            entity,
                            DrugDto[].class);

                    DrugDto[] responseBody = response.getBody();
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
