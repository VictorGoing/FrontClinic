package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.domain.Appointment;
import com.vaadin.flow.component.button.Button;
import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.domain.Patient;
import com.clinic.frontclinic.service.AppointmentService;
import com.clinic.frontclinic.service.DoctorService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route(value = "AllAppointmentView", layout = MainView.class)
@AnonymousAllowed
public class AllAppointmentsView extends VerticalLayout{

    private DoctorService doctorService = DoctorService.getInstance();

    private AppointmentService appointmentService = AppointmentService.getInstance();
    public AllAppointmentsView(){
        Patient patient = ((Patient) VaadinService.getCurrentRequest()
                .getWrappedSession().getAttribute("currentUser"));


        List<Appointment> appointmentList = appointmentService.getAppointmentList(patient.getId());

        for (Appointment appointment: appointmentList){
            Doctor doctor = doctorService.getDoctorById(appointment.getDoctorId().toString());
            String linkTitle = appointment.getDate().toString().replace('T',' ')
                    .replace(".000Z"," ") + ": " + doctor.getSpecialization();
            RouterLink routerLink = new RouterLink(linkTitle,AppointmentView.class);
            routerLink.addAttachListener(event -> {
                VaadinService.getCurrentRequest().getWrappedSession(). removeAttribute("appointment");
                VaadinService.getCurrentRequest().getWrappedSession().setAttribute("appointment",appointment);
            });
            routerLink.setHighlightCondition(HighlightConditions.sameLocation());
            add(routerLink);
        }

    }
}
