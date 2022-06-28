package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "EditAccount", layout = MainView.class)
public class EditAccountView extends VerticalLayout {

    public EditAccountView(){
        addClassName("EditAccount");
        add(new Button("Click me", e -> Notification.show("Hello World")));
    }
}
