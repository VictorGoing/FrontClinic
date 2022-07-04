package com.clinic.frontclinic.view;

import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.service.DoctorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;

@Route("registerDoctor")
@PageTitle("Register | Clinic")
@AnonymousAllowed
public class DoctorRegisterView extends VerticalLayout {

    private DoctorService doctorService = DoctorService.getInstance();
    private Button btnRegister = new Button("Register");

    public DoctorRegisterView() {
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        TextField email = new TextField("email");
        TextField specialization = new TextField("specialization");
        TextField city = new TextField("City");
        TextField startWorkingTime= new TextField("Start of working time");
        TextField endWorkingTime = new TextField("End of working time");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm password");

        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                email, specialization,
                city,
                startWorkingTime, endWorkingTime,
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
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        btnRegister.addClickListener(e -> {
            if(password.getValue().equals(confirmPassword.getValue())) {
                Doctor doctor = new Doctor(firstName.getValue(), lastName.getValue(), specialization.getValue(),
                        city.getValue(), email.getValue(), password.getValue(),
                        Integer.parseInt(startWorkingTime.getValue()), Integer.parseInt(endWorkingTime.getValue()),
                        new ArrayList<Long>());
                doctorService.registerDoctor(doctor);
                new Notification("Patient was added",4000);
            }
            else{
                new Notification("Not matching passwords",4000);
            }
        });

        add(btnRegister);
    }
}
