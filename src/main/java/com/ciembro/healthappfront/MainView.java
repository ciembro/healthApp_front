package com.ciembro.healthappfront;

import com.ciembro.healthappfront.insights.InsightsView;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;

@Route("/")
@Getter
public class MainView extends VerticalLayout {

    private H1 welcomeHeader = new H1();
    private LoginForm loginForm = new LoginForm(this);
    private MenuLayout menuLayout = new MenuLayout(this);

    public MainView() {
        if(VaadinSession.getCurrent().getAttribute("token") == null){
            welcomeHeader.setVisible(false);
            menuLayout.setVisible(false);
            loginForm.setVisible(true);
        }else{
            setWelcomeHeader();
            menuLayout.setVisible(true);
            loginForm.setVisible(false);
        }
        add(welcomeHeader, menuLayout, loginForm);

        setSizeFull();
    }

    public void setWelcomeHeader(){
        String username = (String)VaadinSession.getCurrent().getAttribute("username");
        welcomeHeader.setText("Witaj, " + username + "!");
        welcomeHeader.setVisible(true);
    }

    public void showMenu(){
        welcomeHeader.setVisible(true);
        menuLayout.setVisible(true);
        loginForm.setVisible(false);
    }
}
