package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.Appointment;
import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.domain.Patient;
import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {

    private static AppointmentService appointmentService;

    private AppointmentService() {
    }

    public static AppointmentService getInstance(){
        if(appointmentService == null){
            appointmentService = new AppointmentService();
        }
        return appointmentService;
    }

    public Boolean checkAppointment(Appointment appointment){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> exchange = restTemplate.exchange(
            "http://localhost:8083/v1/appointment/check/",
                HttpMethod.PUT,
                new HttpEntity<Appointment>(appointment),
                Boolean.class);
        return exchange.getBody();
    }

    public void createAppointment(Appointment appointment){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Appointment> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/appointment/create",
                HttpMethod.POST,
                new HttpEntity<Appointment>(appointment),
                Appointment.class);
    }

    public List<Appointment> getAppointmentList(List<Long> appointmentsId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Appointment>> exchange = restTemplate.exchange(
                "http://localhost:8083/v1/appointment/list",
                HttpMethod.GET,
                new HttpEntity<List<Long>>(appointmentsId),
                new ParameterizedTypeReference<List<Appointment>>() {}
        );
        return exchange.getBody();
    }
}
