package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.domain.Patient;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "EditAccount", layout = MainView.class)
@AnonymousAllowed
public class EditAccountView extends VerticalLayout {

    public EditAccountView(){

        Patient patient = ((Patient) VaadinService.getCurrentRequest()
                .getWrappedSession().getAttribute("currentUser"));

        if (patient != null) {
            Text textPatientFirstname = new Text("First name: " + patient.getFirstname());
            Text textPatientLastname = new Text("Last name: " + patient.getLastname());
            Text textPatientEmail = new Text("email: " + patient.getEmail());
            add(new H3(textPatientFirstname));
            add(new H3(textPatientLastname));
            add(new H3(textPatientEmail));
        }
        else {
            VaadinService.getCurrentRequest()
                    .getWrappedSession().removeAttribute("currentUser");
            UI.getCurrent().navigate(PatientLoginView.class);
        }
    }


}
