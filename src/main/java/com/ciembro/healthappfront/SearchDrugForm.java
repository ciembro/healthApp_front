package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.DrugDto;
import com.ciembro.healthappfront.domain.DrugGrid;
import com.ciembro.healthappfront.service.DrugService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;


public class SearchDrugForm extends FormLayout {

    private DrugService drugService = new DrugService();
    private TextField searchField = new TextField("Wyszukaj po nazwie");
    private Button finishButton = new Button("Zakończ");
    private Button searchButton = new Button("Szukaj");
    private DrugGrid drugGrid = new DrugGrid();
    private final MainView mainView;

    public SearchDrugForm(MainView mainView){
        this.mainView = mainView;
        this.setSizeFull();
        setDrugGrid();
        this.setVisible(false);
        drugGrid.setVisible(false);
        add (new VerticalLayout(searchField,
                drugGrid.getDrugGrid(),
                new HorizontalLayout(searchButton, finishButton)));


        searchButton.addClickListener(e -> search());

        finishButton.addClickListener(e -> {
            this.setVisible(false);
            mainView.getDrugGrid().setVisible(true);
            mainView.getAddToListButton().setVisible(true);
            searchField.setValue("");
            drugGrid.setVisible(false);
        });
    }

    private void search(){
        String searchFragment = searchField.getValue();
        drugGrid.setDrugGrid(drugService.searchMatchingDrugs(searchFragment));
        drugGrid.setVisible(true);
    }

    private void setDrugGrid(){

        drugGrid.getDrugGrid().addComponentColumn(drugDto -> {
            Button button = new Button("Dodaj");
//            button.addClickListener(e -> UI.getCurrent().getPage().setLocation("details-view/" + drugDto.getId()));

            return button;
        });
    }


}
