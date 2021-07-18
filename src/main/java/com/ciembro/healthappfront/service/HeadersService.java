package com.ciembro.healthappfront.service;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.http.HttpHeaders;

public enum HeadersService {

    INSTANCE;

    HeadersService(){}

    private HttpHeaders headers;

    private HttpHeaders createHeaders(){
        Object objToken =  VaadinSession.getCurrent().getAttribute("token");
        if (objToken != null) {
            headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + objToken);
            return headers;
        }
        return null;
    }

    public HttpHeaders getHeaders(){
        createHeaders();
        return headers;
    }
}
