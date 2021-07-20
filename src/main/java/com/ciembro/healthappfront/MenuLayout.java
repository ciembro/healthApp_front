package com.ciembro.healthappfront;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class MenuLayout extends VerticalLayout {

    private MainView parent;
    private Button treatmentsButton = new Button("Lista przyjmowanych leków");
    private Button insightsButton = new Button("Dziennik spostrzeżeń");
    private Button reportButton = new Button("Wygeneruj podsumowanie");
    private Button changeLocationButton = new Button("Zmień swoją lokalizację");
    private Button adminButton = new Button("Panel administratora");
    private Button logoutButton = new Button("Wyloguj się");

    private VerticalLayout buttons = new VerticalLayout(adminButton, treatmentsButton,
            insightsButton, reportButton,
            changeLocationButton, logoutButton);

    public MenuLayout(MainView parent) {
        this.parent = parent;

        adminButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("admin"));
        treatmentsButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("treatments"));
        insightsButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("insights"));
        reportButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("report"));
        logoutButton.addClickListener(e -> logout());
        adminButton.setVisible(isAdmin());

        changeLocationButton.addClickListener(e -> {
            parent.getEditUserLayout().setUpForm();
            parent.getEditUserLayout().setVisible(true);
        });

        add(buttons);
        setSizeFull();
    }

    private void logout() {
        VaadinSession.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/");
    }

    private boolean isAdmin(){
        if (VaadinSession.getCurrent().getAttribute("token") != null){
            String roles = (String)VaadinSession.getCurrent().getAttribute("role");
            return roles.contains("ROLE_ADMIN");
        }
        return false;
    }
}
