package com.clinic.frontclinic.service;

import com.clinic.frontclinic.domain.Doctor;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public void addDoctor(Doctor doctor){
        this.doctors.add(doctor);
    }

    private List<Doctor> exampleData(){
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Miroslaw","Bagietka","Dietetyk","Olsztyn"));
        doctors.add(new Doctor("Marcin","Talarek","Stomatolog","Warszawa"));
        doctors.add(new Doctor("Michal", "Cebula", "Kardiolog","Gniezno"));
        return doctors;
    }

    public List<Doctor> findBySpecialization(String specialization){
        return doctors.stream().filter(doctor -> doctor.getSpecialization().contains(specialization)).collect(Collectors.toList());
    }


}