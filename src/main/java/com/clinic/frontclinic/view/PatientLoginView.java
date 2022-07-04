package com.clinic.frontclinic.view;

import com.clinic.frontclinic.domain.ClinicUser;
import com.clinic.frontclinic.domain.Patient;
import com.clinic.frontclinic.service.PatientService;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
        loginForm.addLoginListener(loginEvent -> onLogin(loginEvent));


        RouterLink registerAsPatient = new RouterLink("registerPatient", PatientRegisterView.class);
        RouterLink registerAsDoctor = new RouterLink("registerDoctor",DoctorRegisterView.class);
        add(registerAsPatient,registerAsDoctor);


    }

    private void onLogin(AbstractLogin.LoginEvent loginEvent){
        final String username = loginEvent.getUsername();
        final String password = loginEvent.getPassword();

        Patient patient = patientService.getPatientByEmail(username);
        if(password.equals(patient.getPassword())){
            patient.userDetailsServiceBean();
        }
    }

}

