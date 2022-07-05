package com.clinic.frontclinic;

import com.clinic.frontclinic.domain.ClinicUser;
import com.clinic.frontclinic.view.PatientLoginView;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class FrontClinicApplication extends VaadinWebSecurityConfigurerAdapter {



    public static void main(String[] args) {
        SpringApplication.run(FrontClinicApplication.class, args);
        MainView mainView = new MainView();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);
        setLoginView(http, PatientLoginView.class);
    }


    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean(){
        return new InMemoryUserDetailsManager(
                User.withUsername("Testowy")
                        .password("{noop}1234")
                        .roles("PATIENT")
                        .build()
        );
    }
}
