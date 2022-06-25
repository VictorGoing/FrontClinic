package com.clinic.frontclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontClinicApplication.class, args);
        MainView mainView = new MainView();

    }

}
