package com.clinic.frontclinic.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

public class Patient implements ClinicUser {

    private static String role = "PATIENT";

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<Long> appointmentIdList;


    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole(){
        return role;
    }

    public List<Long> getAppointmentIdList() {
        return appointmentIdList;
    }

    //registerConstructor
    public Patient(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    //downloadPatientConstructor
    public Patient(Long id, String firstname, String lastname, String email, String password, List<Long> appointmentIdList) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.appointmentIdList = appointmentIdList;
    }

    public Patient(){

    }


    @Bean
    public UserDetailsService userDetailsServiceBean(){
        return new InMemoryUserDetailsManager(
                User.withUsername(this.getEmail())
                        .password("{noop}" + this.getPassword())
                        .roles(this.getRole())
                        .build()
        );
    }
}
