package com.ciembro.healthappfront;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.Getter;

@Route("/")
@Getter
public class MainView extends VerticalLayout {

    private H1 welcomeHeader = new H1();
    private LoginForm loginForm = new LoginForm(this);
    private MenuLayout menuLayout = new MenuLayout(this);

    public MainView() {
        welcomeHeader.setVisible(false);
        menuLayout.setVisible(false);
        add(welcomeHeader, menuLayout, loginForm);

        setSizeFull();

    }

    public void setWelcomeHeader(String username){
        welcomeHeader.setText("WITAM, " + username + "!");
        welcomeHeader.setVisible(true);
    }
}
