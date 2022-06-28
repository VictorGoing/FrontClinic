package com.clinic.frontclinic.domain;

import java.time.LocalDateTime;

public class Appointment {
    private LocalDateTime date;
    private int time;
    private Long doctorId;
    private Long UserId;

    public Appointment(int time, Long doctorId, Long userId) {
        this.time = time;
        this.doctorId = doctorId;
        UserId = userId;
    }


    public int getTime() {
        return time;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getUserId() {
        return UserId;
    }

    public static LocalDateTime getStartDateTime(Appointment appointment) {
        return appointment.date;
    }

    public static void setStartDateTime(Appointment appointment, LocalDateTime localDateTime) {
        appointment.date = localDateTime;
    }
}
