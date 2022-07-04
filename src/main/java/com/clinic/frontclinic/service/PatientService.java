package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.domain.Patient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class PatientService {

    private static PatientService patientService;


    private PatientService(){

    }

    public static PatientService getInstance(){
        if(patientService == null){
            patientService = new PatientService();
        }
        return patientService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public String getHello(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/patient/hello/1",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        System.out.println(exchange.getBody());
        System.out.println("Test");
        return null;
    }


    public void registerPatient(Patient patient){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Patient> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/patient/register",
                HttpMethod.POST,
                new HttpEntity<Patient>(patient),
                Patient.class);
        System.out.println(exchange.getBody());
        System.out.println("Test");
    }

    public Patient getPatientByEmail(String email){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Patient> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/patient/getPatientByEmail/" + email,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Patient.class);
        return exchange.getBody();
    }

  /*  public void loginUser(String email, String password){
        User user = exampleData();
        if(user.getEmail().equals(email) && user.getPassword().equals(password)) return exampleData();
        return User;
    }*/


    /*private User exampleData(){

    }*/

}
