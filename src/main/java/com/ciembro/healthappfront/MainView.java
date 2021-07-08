package com.ciembro.healthappfront;

import com.ciembro.healthappfront.service.DrugService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("user-view")
public class MainView extends VerticalLayout {

    private final DrugService drugService = new DrugService();
    public MainView(){
        Button b = new Button("Click");
        b.addClickListener( e -> drugService.getUserDrugList());
        add (
                new H1("Welcome"),
                b
        );

    }
}
