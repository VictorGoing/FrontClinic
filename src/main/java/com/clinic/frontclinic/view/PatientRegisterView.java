package com.clinic.frontclinic.view;

import com.clinic.frontclinic.domain.Patient;
import com.clinic.frontclinic.service.EmailValidationService;
import com.clinic.frontclinic.service.PatientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("registerPatient")
@PageTitle("Register | Clinic")
@AnonymousAllowed
public class PatientRegisterView extends VerticalLayout {

    private PatientService patientService = PatientService.getInstance();
    private EmailValidationService emailValidationService = EmailValidationService.getInstance();
    private Button btnRegister = new Button("Register");

    public PatientRegisterView(){

        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        TextField email = new TextField("Email");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm password");

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                email,
                password, confirmPassword
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2)
        );
        // Stretch the username field over 2 columns
        formLayout.setColspan(email, 2);

        add(formLayout);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        btnRegister.setVisible(false);
        email.addValueChangeListener(event ->{
            Notification.show("Please wait, Email validation in progress");
            if(emailValidationService.checkEmail(email.getValue()))
                btnRegister.setVisible(true);
            else
                btnRegister.setVisible(false);
        });
        btnRegister.addClickListener(e -> {
            if(password.getValue().equals(confirmPassword.getValue())) {
                Patient patient = new Patient(firstName.getValue(), lastName.getValue(), email.getValue(), password.getValue());
                patientService.registerPatient(patient);
                new Notification("Patient was added",4000);
            }
            else{
                new Notification("Not matching passwords",4000);
            }
            });

        add(btnRegister);
    }


}
