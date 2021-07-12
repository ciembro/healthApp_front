package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.DrugDto;
import com.ciembro.healthappfront.service.DrugService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import javax.swing.*;
import java.util.List;


@Route("user-view")
public class MainView extends VerticalLayout {

    private final DrugService drugService = new DrugService();
    private Grid<DrugDto> drugGrid = new Grid<>(DrugDto.class);

    public MainView(){
        add (new H1("Witaj, " + VaadinSession.getCurrent().getAttribute("username") + "!"));

        getUserDrugList();
        updateDrugGrid();

        drugGrid.addItemDoubleClickListener(e -> UI.getCurrent().getPage().setLocation("details-view"));

    }

    private void getUserDrugList(){
        List<DrugDto> drugs = drugService.getUserDrugList();
        Label emptyList = new Label("Obecnie Twoja lista leków jest pusta");
        if (drugs.isEmpty()){
            add(emptyList);
        } else {
            this.remove(emptyList);
            drugGrid.removeColumnByKey("id");
            drugGrid.removeColumnByKey("activeSubstance");
            drugGrid.removeColumnByKey("brand");
            drugGrid.removeColumnByKey("dose");
            drugGrid.removeColumnByKey("leafletUrl");
            drugGrid.removeColumnByKey("tradeName");
            drugGrid.removeColumnByKey("commonName");
            add(drugGrid);
            drugGrid.addColumn(drugDto -> {
                String tradeName = drugDto.getTradeName();
                return tradeName == null ? "-" : tradeName;
            }).setHeader("Nazwa produktu leczniczego");
            drugGrid.addColumn(drugDto -> {
                String commonName = drugDto.getCommonName();
                return commonName == null ? "-" : commonName;
            }).setHeader("Nazwa powszechna");
            drugGrid.addColumn(drugDto -> {
                String dose = drugDto.getDose();
                return dose == null ? "-" : dose;
            }).setHeader("Moc");
            drugGrid.addColumn(drugDto -> {
                String brand = drugDto.getBrand();
                return brand == null ? "-" : brand;
            }).setHeader("Podmiot odpowiedzialny");
        }
    }

    private void updateDrugGrid(){
        drugGrid.setItems(drugService.getUserDrugList());
    }

    private void onDoubleClick(){


    }
}
