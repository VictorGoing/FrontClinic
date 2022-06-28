package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.exceptions.DoctorNotFoundException;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

import java.util.*;
import java.util.stream.Collectors;

public class DoctorService {
    private List<Doctor> doctors;
    private static DoctorService doctorService;

    private DoctorService(){
        this.doctors = exampleData();
    }

    public static DoctorService getInstance(){
        if(doctorService == null){
            doctorService = new DoctorService();
        }
        return doctorService;
    }

    public List<Doctor> getDoctors(){
        return new ArrayList<>(doctors);
    }

    public Doctor getDoctorById(Optional<String> doctorId) {
        for(Doctor doctor: doctors) {
            if (doctor.getId() == Long.parseLong(doctorId.get())) return doctor;
        }
        return doctors.get(0);
    }

    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }

    private List<Doctor> exampleData(){
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1L,"Miroslaw","Bagietka","Dietetyk","Olsztyn"));
        doctors.add(new Doctor(2L,"Marcin","Talarek","Stomatolog","Warszawa"));
        doctors.add(new Doctor(3L,"Michal", "Cebula", "Kardiolog","Gniezno"));
        return doctors;
    }

    public List<Doctor> findBySpecialization(String specialization){
        return doctors.stream().filter(doctor -> doctor.getSpecialization().contains(specialization)).collect(Collectors.toList());
    }


}