package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.DrugDto;
import com.ciembro.healthappfront.domain.DrugGrid;
import com.ciembro.healthappfront.domain.SideEffectDto;
import com.ciembro.healthappfront.service.DrugService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
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
    private Label matchesNotFoundLabel = new Label("Nie znaleziono pasujących wyników.");
    private final MainView mainView;

    public SearchDrugForm(MainView mainView){
        this.mainView = mainView;
        this.setSizeFull();
        addDrugGridButton();
        this.setVisible(false);
        drugGrid.setVisible(false);
        matchesNotFoundLabel.setVisible(false);

        add (new VerticalLayout(searchField,
                matchesNotFoundLabel,
                drugGrid.getDrugGrid(),
                new HorizontalLayout(searchButton, finishButton)));

        searchButton.addClickListener(e -> search());

        finishButton.addClickListener(e -> {
            this.setVisible(false);
            mainView.getDrugGrid().setVisible(true);
            mainView.getAddToListButton().setVisible(true);
            searchField.setValue("");
            drugGrid.setVisible(false);
            mainView.refresh();
        });
    }

    private void search(){
        String searchFragment = searchField.getValue();
        List<DrugDto> drugs = drugService.searchMatchingDrugs(searchFragment);
        if (drugs.isEmpty()){
            drugGrid.setVisible(false);
            matchesNotFoundLabel.setVisible(true);
        } else {
            matchesNotFoundLabel.setVisible(false);
            drugGrid.setDrugGrid(drugs);
            drugGrid.setVisible(true);
        }

    }

    private void addDrugGridButton(){
        drugGrid.getDrugGrid().addComponentColumn(drugDto -> {
            Button button = new Button("Dodaj");
            button.addClickListener(e -> {
                drugService.addDrugToUserList(drugDto);
            });
            return button;
        });
    }
}
