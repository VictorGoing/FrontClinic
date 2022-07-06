package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.CovidInfo;
import com.clinic.frontclinic.domain.Patient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CovidInfoServices {

    private static CovidInfoServices covidInfoServices;

    public CovidInfoServices() {
    }

    public static CovidInfoServices getInstance(){
        if(covidInfoServices == null){
            covidInfoServices = new CovidInfoServices();
        }
        return covidInfoServices;
    }

    public CovidInfo checkCountry(String country){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CovidInfo> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/covidInfo/" + country.toLowerCase(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                CovidInfo.class);
        return exchange.getBody();
    }
}
