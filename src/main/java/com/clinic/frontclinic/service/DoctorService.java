package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.domain.Patient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class DoctorService {
    private static DoctorService doctorService;

    private DoctorService(){
    }

    public static DoctorService getInstance(){
        if(doctorService == null){
            doctorService = new DoctorService();
        }
        return doctorService;
    }



    public Doctor getDoctorById(String doctorId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Doctor> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/doctor/" + doctorId,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Doctor.class);
        return exchange.getBody();
    }

    public List<Doctor> getListOfDoctors(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Doctor>> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/doctor/getListOfDoctors",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Doctor>>() {});
        return exchange.getBody();
    }

    public void registerDoctor(Doctor doctor){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Doctor> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/doctor/register",
                HttpMethod.POST,
                new HttpEntity<Doctor>(doctor),
                Doctor.class);
        System.out.println(exchange.getBody());
        System.out.println("Test");
    }


}