package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.service.DoctorService;
import com.clinic.frontclinic.exceptions.DoctorNotFoundException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;


import java.util.Locale;
import java.util.Optional;

@Route(value = "DoctorProfile/:doctorId", layout = MainView.class)
public class DoctorProfileView extends Div implements BeforeEnterObserver{

    private DoctorService doctorService = DoctorService.getInstance();
    private Doctor doctor;
    private String doctorId;


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        doctorId = event.getRouteParameters().get("doctorId").get();
    }



    public DoctorProfileView(){
        addClassName("DoctorProfile");
        System.out.println(doctorId);
        //Doctor doctor = doctorService.getDoctorById(Long.parseLong((doctorId.get())));
        //add(new Text(doctor.getFirstname() + " " + doctor.getLastname()));
        //add(new Text("Specialization: " + doctor.getSpecialization()));


        add(new Button("Click me", e -> Notification.show("Hello World")));
        Locale finnishLocale = new Locale("fi", "FI");

        DatePicker datePicker = new DatePicker("Select a date:");
        datePicker.setLocale(finnishLocale);


    }
}

