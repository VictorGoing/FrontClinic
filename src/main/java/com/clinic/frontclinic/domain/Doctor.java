package com.clinic.frontclinic.domain;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class Doctor {
    private Long id;
    private String firstname;
    private String lastname;
    private String specialization;
    private String city;
    private int startWorkingHours;
    private int endWorkingHours;

    public Doctor(Long id,String firstname, String lastname, String specialization, String city,int startWorking, int endWorking) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.city = city;
        this.startWorkingHours = startWorking;
        this.endWorkingHours = endWorking;
    }

    public Long getId(){
        return id;
    }

   /* public Optional<Long> getId(){
        return Optional.ofNullable(id);
    }*/

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getCity() {
        return city;
    }

    public int getStartWorkingHours() {
        return startWorkingHours;
    }

    public int getEndWorkingHours() {
        return endWorkingHours;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (firstname != null ? !firstname.equals(doctor.firstname) : doctor.firstname != null) return false;
        if (lastname != null ? !lastname.equals(doctor.lastname) : doctor.lastname != null) return false;
        return specialization != null ? specialization.equals(doctor.specialization) : doctor.specialization == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }
}


