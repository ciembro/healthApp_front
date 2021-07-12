package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.domain.DrugDto;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;


@Service
public class DrugService {

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1";

    public List<DrugDto> getUserDrugList(){

        try {
            Object objToken =  VaadinSession.getCurrent().getAttribute("token");
            if (objToken != null){
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + objToken);

                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<DrugDto[]> response = restTemplate.exchange(
                        BASE_URL + "/drugs/all",
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
