package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.DrugDto;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("details-view")
public class DrugDetailsView extends VerticalLayout {

    public DrugDetailsView(DrugDto drugDto){
        add(new Label(drugDto.getCommonName()));
    }
}
