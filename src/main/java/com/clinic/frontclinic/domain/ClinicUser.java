package com.clinic.frontclinic.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface ClinicUser {
    String getEmail();
    String getPassword();
    String getRole();
}
