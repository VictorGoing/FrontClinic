package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;

import com.clinic.frontclinic.domain.Patient;
import com.clinic.frontclinic.service.PatientService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.AbstractLogin;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;


@Route("login")
@PageTitle("Login | Clinic")
public class PatientLoginView extends VerticalLayout {

    PatientService patientService = PatientService.getInstance();
    public PatientLoginView(){
        LoginForm loginForm = new LoginForm();
        loginForm.setForgotPasswordButtonVisible(false);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(loginForm);
        loginForm.addLoginListener(loginEvent -> {
            try {
                Patient patient = patientService.getPatientByEmail(loginEvent.getUsername());

                if (patient.getPassword().equals(loginEvent.getPassword())) {
                    UI.getCurrent().navigate(MainView.class);
                    saveCurrentPatient(patient);
                } else {
                    new Notification("Wrong credentials",6000);
                }
            } catch (HttpServerErrorException e){
                new Notification("User not found",6000);
            }

        });

        RouterLink registerAsPatient = new RouterLink("registerPatient", PatientRegisterView.class);
        RouterLink registerAsDoctor = new RouterLink("registerDoctor",DoctorRegisterView.class);
        add(registerAsPatient,registerAsDoctor);


    }

    public void saveCurrentPatient(Patient patient){
        VaadinService.getCurrentRequest().getWrappedSession().setAttribute("currentUser",patient);
    }

    /*private void onLogin(AbstractLogin.LoginEvent loginEvent){
        final String username = loginEvent.getUsername();
        final String password = loginEvent.getPassword();

        Patient patient = patientService.getPatientByEmail(username);
        if(password.equals(patient.getPassword())){
            patient.userDetailsServiceBean();
            UI.getCurrent().navigate(MainView.class);
        }else{
            Notification.show("Wrong credentials");
        }
    }*/

}

