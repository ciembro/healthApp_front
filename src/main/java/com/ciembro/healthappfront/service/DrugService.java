package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.SearchDrugForm;
import com.ciembro.healthappfront.domain.DrugDto;
import com.ciembro.healthappfront.domain.SideEffectDto;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;


public class DrugService {

    private RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1";

    private HttpHeaders createHeaders(){
        Object objToken =  VaadinSession.getCurrent().getAttribute("token");
        if (objToken != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + objToken);
            return headers;
        }
        return null;
    }

    public List<DrugDto> getUserDrugList(){
        try {
            HttpHeaders headers = createHeaders();
            if (headers != null){
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

    public void removeDrugFromUserList(DrugDto drugDto){
        try {
            HttpHeaders headers = createHeaders();
            if (headers != null){
                HttpEntity<DrugDto> entity = new HttpEntity<>(drugDto, headers);

                restTemplate.exchange(
                        BASE_URL + "/drugs",
                        HttpMethod.DELETE,
                        entity,
                        DrugDto.class);
            }
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
    }

    public List<DrugDto> searchMatchingDrugs(String text){
        try {
            HttpHeaders headers = createHeaders();
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

    public void addDrugToUserList(DrugDto drugDto) {
        try {
            HttpHeaders headers = createHeaders();
            if (headers != null){

                HttpEntity<DrugDto> entity = new HttpEntity<>(drugDto, headers);

                restTemplate.exchange(
                        BASE_URL + "/drugs",
                        HttpMethod.POST,
                        entity,
                        DrugDto[].class);
            }
        } catch(RestClientException e){
                System.out.println(e.getMessage());
            }
        }

        public DrugDto getDrugById(long id){
            try {
                HttpHeaders headers = createHeaders();
                if (headers != null){
                    HttpEntity<String> entity = new HttpEntity<>(headers);
                    ResponseEntity<DrugDto> response = restTemplate.exchange(
                            BASE_URL + "/drugs/" + id,
                            HttpMethod.GET,
                            entity,
                            DrugDto.class);

                    return response.getBody();
                }
            } catch (RestClientException e){
                System.out.println(e.getMessage());
            }
            return null;
    }


}
