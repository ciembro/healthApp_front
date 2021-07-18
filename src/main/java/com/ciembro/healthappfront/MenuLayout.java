package com.ciembro.healthappfront;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MenuLayout extends VerticalLayout {

    private MainView parent;
    private Button treatmentsButton = new Button("Leki");
    private Button insightsButton = new Button("Dziennik spostrzeżeń");
    private VerticalLayout buttons = new VerticalLayout(treatmentsButton, insightsButton);

    public MenuLayout(MainView parent) {
        this.parent = parent;

        treatmentsButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("treatments"));
        insightsButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("insights"));

        add(buttons);
        setSizeFull();
    }
}
