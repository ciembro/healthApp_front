package com.ciembro.healthappfront.service;

import com.ciembro.healthappfront.domain.JwtToken;
import com.ciembro.healthappfront.domain.UserDto;
import com.ciembro.healthappfront.domain.UserToRegisterDto;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String BASE_URL = "http://localhost:8080/v1";

    public boolean authenticateUser(UserDto userDto) {

        try {
            JwtToken jwtToken = restTemplate.postForObject(BASE_URL + "/authenticate", userDto,
                    JwtToken.class);
            if (jwtToken != null){
                VaadinSession.getCurrent().setAttribute("token", jwtToken.getToken());
                VaadinSession.getCurrent().setAttribute("username", userDto.getUsername());
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
