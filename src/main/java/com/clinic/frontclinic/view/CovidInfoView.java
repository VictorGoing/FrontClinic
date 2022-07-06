package com.clinic.frontclinic.view;

import com.clinic.frontclinic.MainView;
import com.clinic.frontclinic.service.CovidInfoServices;
import com.clinic.frontclinic.domain.CovidInfo;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.client.HttpServerErrorException;

import java.awt.*;

@Route(value ="CovieInfo", layout = MainView.class)
@AnonymousAllowed
public class CovidInfoView extends VerticalLayout {

    private CovidInfoServices covidInfoServices = CovidInfoServices.getInstance();

    public CovidInfoView(){
        Text text1 = new Text("");
        Text text2 = new Text("");
        Text text3 = new Text("");
        Text text4 = new Text("");
        TextField textField = new TextField();
        Button button = new Button("check");
        button.addClickListener(event -> {
            try{
            CovidInfo covidInfo = covidInfoServices.checkCountry(textField.getValue());
            text1.setText("Confirmed: " + covidInfo.getConfirmed());
            text2.setText("Recovered: " + covidInfo.getRecovered());
            text3.setText("Deaths: " + covidInfo.getDeaths());
            text4.setText("Last update: " + covidInfo.getLastUpdate()
                    .replace('T',' ').replace(".000Z"," "));

            add(new VerticalLayout(text1));
            add(new VerticalLayout(text2));
            add(new VerticalLayout(text3));
            add(new VerticalLayout(text4));
            }catch (HttpServerErrorException e){
                Notification.show("Wrong country name");
            }
        });

        add(new HorizontalLayout(new Text("Input country"),textField),button);
    }
}
