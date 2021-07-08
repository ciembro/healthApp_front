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

    public List<DrugDto> getUserDrugList(){

        try {
            Object objToken =  VaadinSession.getCurrent().getAttribute("token");
            if (objToken != null){
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + objToken);

                HttpEntity<String> entity = new HttpEntity<>(headers);

                ResponseEntity<DrugDto[]> response = restTemplate.exchange(
                        getUri(),
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

    private URI getUri(){
        return UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1" + "/user/drugs/all")
                .build()
                .encode()
                .toUri();
    }
}
