package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.domain.Appointment;
import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.domain.Patient;
import com.clinic.frontclinic.service.AppointmentService;
import com.clinic.frontclinic.service.DoctorService;
import com.clinic.frontclinic.service.PatientService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.time.Duration;
import java.time.LocalTime;

@Route(value = "AppointmentView", layout = MainView.class)
@AnonymousAllowed
public class AppointmentView extends VerticalLayout {

    private DoctorService doctorService = DoctorService.getInstance();
    private PatientService patientService = PatientService.getInstance();
    private AppointmentService appointmentService = AppointmentService.getInstance();

    public AppointmentView() {
        Appointment appointment = ((Appointment) VaadinService.getCurrentRequest()
                .getWrappedSession().getAttribute("appointment"));

        Doctor doctor = doctorService.getDoctorById(appointment.getDoctorId().toString());
        Patient patient = patientService.getPatientById(appointment.getPatientId());

        Text textDateAppointment = new Text("Date and time of appointment: " + appointment.getDate());
        Text textDoctorSpecialization = new Text("Doctor Specialization: " + doctor.getSpecialization());
        Text textDoctorName = new Text("Doctor name: " + doctor.getFirstname() + " " + doctor.getLastname());
        add(new H3(textDateAppointment));
        add(new H3(textDoctorSpecialization));
        add(new H3(textDoctorName));

        Button btnEditAppointment = new Button("edit Appointment");
        btnEditAppointment.addClickListener(event -> {
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
                        appointmentService.editAppointment(
                                new Appointment(dateTimePicker.getValue(), doctor.getId(), patient.getId()));
                        Notification.show("Your appointment has been booked");
                    }
                    Notification.show("This time was already booked." +
                            "Choose other day or hour");

                }
            });
            add(btnBookAppointment);
        });

        add(btnEditAppointment);
    }
}
