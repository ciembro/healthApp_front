package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.dto.*;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate = new RestTemplate();
    private HeadersService headersService = HeadersService.INSTANCE;
    private final static String BASE_URL = "http://localhost:8080/v1";
    private LocationInterpreter locationInterpreter = LocationInterpreter.INSTANCE;

    public void changeUserLocation(String location){
        try {
            HttpHeaders headers = headersService.getHeaders();
            if (headers != null){
                HttpEntity<String> entity = new HttpEntity<>(headers);
                location = locationInterpreter.prepareQueryParam(location);
                String username = (String)VaadinSession.getCurrent().getAttribute("username");
                restTemplate.exchange(
                        BASE_URL + "/" + username + "/" +location,
                        HttpMethod.PUT,
                        entity,
                        ResponseEntity.class);
            }
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean authenticateUser(UserDto userDto) {
        try {
            AuthenticationResponse authenticationResponse = restTemplate.postForObject(BASE_URL + "/authenticate", userDto,
                    AuthenticationResponse.class);
            if (authenticationResponse != null){
                VaadinSession.getCurrent().setAttribute("token", authenticationResponse.getToken());
                VaadinSession.getCurrent().setAttribute("username", userDto.getUsername());
                VaadinSession.getCurrent().setAttribute("role", authenticationResponse.getRole());
                return true;
            }
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean registerUser(UserToRegisterDto userDto){
        try {
           Boolean isRegistered = restTemplate.postForObject(BASE_URL + "/register", userDto,Boolean.class);
          if (isRegistered != null){
              return isRegistered;
          }
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


}
