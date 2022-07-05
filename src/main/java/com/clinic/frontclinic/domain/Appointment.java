package com.clinic.frontclinic.domain;

import java.time.LocalDateTime;

public class Appointment {

    private Long id;
    private LocalDateTime date;
    private Long doctorId;
    private Long patientId;

    public Appointment(LocalDateTime date, Long doctorId, Long patientId) {
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public static LocalDateTime getStartDateTime(Appointment appointment) {
        return appointment.date;
    }

    public static void setStartDateTime(Appointment appointment, LocalDateTime localDateTime) {
        appointment.date = localDateTime;
    }
}
