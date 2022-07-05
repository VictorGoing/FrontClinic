package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.domain.Appointment;
import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.domain.Patient;
import com.clinic.frontclinic.service.AppointmentService;
import com.clinic.frontclinic.service.DoctorService;
import com.clinic.frontclinic.exceptions.DoctorNotFoundException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;


import javax.annotation.security.RolesAllowed;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Optional;

@Route(value = "DoctorProfile/:doctorId", layout = MainView.class)
@AnonymousAllowed
public class DoctorProfileView extends Div implements BeforeEnterObserver {

    private DoctorService doctorService = DoctorService.getInstance();
    private AppointmentService appointmentService = AppointmentService.getInstance();
    private Doctor doctor;
    private String doctorId;


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Patient patient = (Patient) VaadinService.getCurrentRequest().
                getWrappedSession().getAttribute("currentUser");

        Optional<String> optionalDoctorId = event.getRouteParameters().get("doctorId");
        if (optionalDoctorId.isPresent()) {
            doctorId = optionalDoctorId.get();
        }
        doctor = doctorService.getDoctorById(doctorId);
        Text textDoctorFirstname = new Text("First name: " + doctor.getFirstname());
        Text textDoctorLastname = new Text("Last name: " + doctor.getLastname());
        Text textDoctorSpecialization = new Text("Specialization: " + doctor.getSpecialization());
        Text textDoctorCity = new Text("City: " + doctor.getCity());


        add(new H3(textDoctorFirstname));
        add(new H3(textDoctorLastname));
        add(new H3(textDoctorSpecialization));
        add(new H3(textDoctorCity));

        Locale finnishLocale = new Locale("fi", "FI");

        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLabel("Select date and time");
        dateTimePicker.setHelperText("Format: DD/MM/YYYY and HH:MM");
        dateTimePicker.setDatePlaceholder("Date");
        dateTimePicker.setTimePlaceholder("Time");
        dateTimePicker.setStep(Duration.ofHours(1));

        dateTimePicker.setLabel("Appointment date and time");
        dateTimePicker.setHelperText("Open Mondays-Fridays, " + doctor.getStartWorkingHour() +
                ":00 - " + doctor.getEndWorkingHour() + ":00");
        add(dateTimePicker);

        Binder<Appointment> binder = new Binder<>(Appointment.class);
        binder.forField(dateTimePicker).withValidator(startDateTime -> {
            boolean validWeekDay = startDateTime.getDayOfWeek().getValue() >= 1
                    && startDateTime.getDayOfWeek().getValue() <= 5;
            return validWeekDay;
        }, "The selected day of week is not available").withValidator(startDateTime -> {
            LocalTime startTime = LocalTime.of(startDateTime.getHour(), startDateTime.getMinute());
            boolean validTime = !(LocalTime.of(doctor.getStartWorkingHour(), 0).isAfter(startTime)
                    || LocalTime.of(doctor.getEndWorkingHour(), 0).isBefore(startTime));
            return validTime;
        }, "The selected time is not available").bind(Appointment::getStartDateTime, Appointment::setStartDateTime);

        Button btnBookAppointment = new Button("Book Appointmnet");
        btnBookAppointment.addClickListener(event1 -> {
            if(binder.isValid()){
               if(appointmentService.checkAppointment(new Appointment(dateTimePicker.getValue(),doctor.getId(),patient.getId()))){
                   appointmentService.createAppointment(
                           new Appointment(dateTimePicker.getValue(),doctor.getId(), patient.getId()));
                   Notification.show("Your appointment has been booked");
               }
                Notification.show("This time was already booked." +
                        "Choose other day or hour");

            }
        });
        add(btnBookAppointment);
    }

    private void bookAppointment(Appointment appointment){
        ;
    }

    public DoctorProfileView() {
        addClassName("DoctorProfile");

    }


}

