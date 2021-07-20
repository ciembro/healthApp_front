package com.ciembro.healthappfront.treatment;

import com.ciembro.healthappfront.dto.DrugDto;
import com.ciembro.healthappfront.service.DrugService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class SearchDrugLayout extends VerticalLayout {

    private UserTreatmentsView parent;
    private TextField searchDrugName = new TextField("Wpisz nazwę");
    private Button searchButton = new Button("Szukaj");
    private Button cancelButton = new Button("Anuluj");
    private Grid<DrugDto> drugGrid = new Grid<>(DrugDto.class);
    private DrugService drugService = new DrugService();

    public SearchDrugLayout(UserTreatmentsView parent){
        this.parent = parent;
        HorizontalLayout search = new HorizontalLayout(searchDrugName, searchButton, cancelButton);
        search.setWidthFull();
        search.setAlignItems(Alignment.END);
//        search.getStyle().set("border", "1px solid red");
//        getStyle().set("border", "1px solid");
        drugGrid.setColumns("internationalName", "tradeName", "dosage", "brand");
        drugGrid.setSizeFull();
        drugGrid.setVisible(false);

        drugGrid.addComponentColumn(drugDto -> {
            Button button = new Button("Dodaj");
            button.addClickListener(e -> {
                drugGrid.setVisible(false);
                setHeight(null);
                parent.getUserTreatmentAddForm().setUpForm(drugDto);
                parent.getUserTreatmentAddForm().setVisible(true);
                searchDrugName.clear();
            });
            return button;
        });

        add(search, drugGrid);
        searchButton.addClickListener(e -> search());
        cancelButton.addClickListener(e -> {
            drugGrid.setVisible(false);
            setHeight(null);
        });
    }

    private void search(){
        refreshDrugGridItems();
        drugGrid.setVisible(true);
        setSizeFull();
    }

    private void refreshDrugGridItems(){
        drugGrid.setItems(drugService.searchMatchingDrugs(searchDrugName.getValue()));
    }

}
