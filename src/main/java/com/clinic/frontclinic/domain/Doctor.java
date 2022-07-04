package com.clinic.frontclinic.domain;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class Doctor implements ClinicUser {
    private static String role = "DOCTOR";

    private Long id;
    private String firstname;
    private String lastname;
    private String specialization;
    private String city;
    private String email;
    private String password;
    private int startWorkingHour;
    private int endWorkingHour;
    private List<Long> appointmentsId;

    public Doctor(Long id, String firstname, String lastname, String specialization, String city,
                  String email, String password, int startWorkingHour, int endWorkingHour, List<Long> appointmentsId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.city = city;
        this.email = email;
        this.password = password;
        this.startWorkingHour = startWorkingHour;
        this.endWorkingHour = endWorkingHour;
        this.appointmentsId = appointmentsId;
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

    public int getStartWorkingHour() {
        return startWorkingHour;
    }

    public int getEndWorkingHour() {
        return endWorkingHour;
    }

    public String getRole(){
        return role;
    }

    public String getEmail(){return email;}

    public String getPassword(){return password;}

    public List<Long> getAppointmentsId() {
        return appointmentsId;
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


    public Doctor(String firstname, String lastname, String specialization, String city, String email, String password,
                  int startWorkingHour, int endWorkingHour, List<Long> appointmentsId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.city = city;
        this.email = email;
        this.password = password;
        this.startWorkingHour = startWorkingHour;
        this.endWorkingHour = endWorkingHour;
        this.appointmentsId = appointmentsId;
    }
}


