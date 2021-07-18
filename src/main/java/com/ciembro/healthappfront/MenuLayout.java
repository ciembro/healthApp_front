package com.ciembro.healthappfront;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MenuLayout extends VerticalLayout {

    private MainView parent;
    private Button treatmentsButton = new Button("Lista przyjmowanych leków");
    private Button insightsButton = new Button("Dziennik spostrzeżeń");
    private Button reportButton = new Button("Wygeneruj podsumowanie");
    private VerticalLayout buttons = new VerticalLayout(treatmentsButton, insightsButton, reportButton);

    public MenuLayout(MainView parent) {
        this.parent = parent;

        treatmentsButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("treatments"));
        insightsButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("insights"));
        reportButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("report"));

        add(buttons);
        setSizeFull();
    }
}
