package com.clinic.frontclinic;

import com.clinic.frontclinic.domain.User;
import com.clinic.frontclinic.view.EditAccountView;
import com.clinic.frontclinic.view.SearchDoctorView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class MainView extends AppLayout {

    private Tab tabCovidInfo = new Tab("Covid 19 INFO");
    private Tab tabEditAccount = new Tab("Edit account");
    private Tab tabSearchDoctors = new Tab("Search doctors");
    private Tab tabVisits = new Tab("Visits");


    public MainView(){
        //add(new Button("Click me", e -> Notification.show("Hello World")));
        createNavbar();
        createTabs();

    }

    private void createNavbar(){
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Clinic");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        addToNavbar(toggle, title);
    }

    private void createTabs(){
        /*Tabs tabs = new Tabs();
        tabs.add(tabSearchDoctors);
        tabs.addSelectedChangeListener(e -> tabsListener(1));
        tabs.add(tabVisits);
        tabs.addSelectedChangeListener(e -> tabsListener(2));
        tabs.add(tabEditAccount);
        tabs.addSelectedChangeListener(e -> tabsListener(3));
        tabs.add(tabCovidInfo);
        tabs.addSelectedChangeListener(e -> tabsListener(4));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);*/

        RouterLink search_doctor = new RouterLink("Search doctor", SearchDoctorView.class);
        search_doctor.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink edit_account = new RouterLink("Edit Account", EditAccountView.class);
        edit_account.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(search_doctor,edit_account));

    }
}
