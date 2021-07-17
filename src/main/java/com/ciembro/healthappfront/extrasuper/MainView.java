package com.ciembro.healthappfront.extrasuper;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/")
public class MainView extends VerticalLayout {

    private H1 welcomeHeader = new H1();
    private LoginForm loginForm = new LoginForm(this);

    public MainView() {
        welcomeHeader.setVisible(false);
        add(welcomeHeader, loginForm);

        setSizeFull();

    }

    public void setWelcomeHeader(String username){
        welcomeHeader.setText("WITAM, " + username + "!");
        welcomeHeader.setVisible(true);
    }
}
