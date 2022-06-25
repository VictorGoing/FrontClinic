package com.clinic.frontclinic.filter;

/*import com.clinic.frontclinic.domain.Doctor;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

public class DoctorFilter {
    private final GridListDataView<Doctor> dataView;

    private String firstname;
    private String lastname;
    private String specialization;
    private String city;

    public DoctorFilter(GridListDataView<Doctor> dataView) {
        this.dataView = dataView;
        this.dataView.addFilter(this::test);
    }

    public void se

    public boolean test(Doctor doctor){
        boolean matchesFirstname = matches(doctor.getFirstname(), firstname);
        boolean matchesLastname = matches(doctor.getLastname(), lastname);
        boolean matchesSpecialization = matches(doctor.getSpecialization(), specialization);
        boolean matchesCity = matches(doctor.getCity(), city);

        return matchesFirstname && matchesLastname && matchesSpecialization && matchesCity;
    }

    private boolean matches(String value, String searchTerm){
        return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}*/
