package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.domain.Doctor;
import com.clinic.frontclinic.service.DoctorService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.function.Consumer;

@Route(value = "SearchDoctors", layout = MainView.class)
public class SearchDoctorView extends VerticalLayout {
    private DoctorService doctorService = DoctorService.getInstance();
    private Grid<Doctor> grid = new Grid<>(Doctor.class,false);

    private SearchDoctorView(){

        addClassName("SearchDoctors");
        Grid.Column<Doctor> firstnameColumn = grid.addColumn(Doctor::getFirstname);
        Grid.Column<Doctor> lastnameColumn = grid.addColumn(Doctor::getLastname);
        Grid.Column<Doctor> specializationColumn = grid.addColumn(Doctor::getSpecialization);
        Grid.Column<Doctor> cityColumn = grid.addColumn(Doctor::getCity);

        List<Doctor> doctors = doctorService.getDoctors();
        GridListDataView<Doctor> dataView = grid.setItems(doctors);
        DoctorFilter doctorFilter = new DoctorFilter(dataView);

        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(firstnameColumn).setComponent(
                createFilterHeader("Firstname", doctorFilter::setFirstname));
        headerRow.getCell(lastnameColumn).setComponent(
                createFilterHeader("Lastname", doctorFilter::setLastname));
        headerRow.getCell(specializationColumn).setComponent(
                createFilterHeader("Specialization", doctorFilter::setSpecialization));
        headerRow.getCell(cityColumn).setComponent(
                createFilterHeader("City", doctorFilter::setCity));
        setSizeFull();

        add(grid);
    }

    private static Component createFilterHeader(String labelText,
                                                Consumer<String> filterChangeConsumer) {
        Label label = new Label(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }

    private static class DoctorFilter {
        private final GridListDataView<Doctor> dataView;

        private String firstname;
        private String lastname;
        private String specialization;
        private String city;

        public DoctorFilter(GridListDataView<Doctor> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::test);
        }

        public void setFirstname(String firstname){
            this.firstname = firstname;
            this.dataView.refreshAll();
        }

        public void setLastname(String lastname){
            this.lastname = lastname;
            this.dataView.refreshAll();
        }

        public void setSpecialization(String specialization){
            this.specialization = specialization;
            this.dataView.refreshAll();
        }

        public void setCity(String city){
            this.city = city;
            this.dataView.refreshAll();
        }

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
    }
}


