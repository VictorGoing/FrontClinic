package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.CovidInfo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EmailValidationService {

    private static EmailValidationService emailValidationService;

    public EmailValidationService() {
    }

    public static EmailValidationService getInstance(){
        if(emailValidationService == null){
            emailValidationService = new EmailValidationService();
        }
        return emailValidationService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public Boolean checkEmail(String email){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/email/" + email.toLowerCase(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Boolean.class);
        return exchange.getBody();
    }
}
