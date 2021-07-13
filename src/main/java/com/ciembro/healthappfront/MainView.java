package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.DrugDto;
import com.ciembro.healthappfront.domain.DrugGrid;
import com.ciembro.healthappfront.domain.UserDto;
import com.ciembro.healthappfront.service.DrugService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import java.util.List;


@Route("user-view")
public class MainView extends VerticalLayout {

    private final DrugService drugService = new DrugService();
    private DrugGrid drugGrid = new DrugGrid();
    private final Binder<DrugDto> binder = new Binder<>(DrugDto.class);
    private Button addToListButton = new Button("Dodaj lek");
    private SearchDrugForm searchDrugForm = new SearchDrugForm(this);
    private Label emptyList = new Label("Obecnie Twoja lista leków jest pusta");
    private DrugDto drugDto;

    public MainView(){
        add (new H1("Witaj, " + VaadinSession.getCurrent().getAttribute("username") + "!"));
        addDrugGridButtons();
        addToListButton.addClickListener(e -> {
            searchDrugForm.setVisible(true);
            drugGrid.setVisible(false);
            addToListButton.setVisible(false);
        });

        drugGrid.getDrugGrid().setSizeFull();
        setSizeFull();
        refresh();
        add(addToListButton,
                emptyList,
                searchDrugForm,
                drugGrid.getDrugGrid());

    }

    private void addDrugGridButtons(){
        drugGrid.getDrugGrid().addComponentColumn(drugDto -> {
            Button button = new Button("Szczegóły");
            button.addClickListener(e -> UI.getCurrent().getPage().setLocation("details-view/" + drugDto.getId()));
            return button;
        });

        drugGrid.getDrugGrid().addComponentColumn(drugDto -> {
            Button button = new Button("Usuń");
            button.addClickListener(e -> {
                drugService.removeDrugFromUserList(drugDto);
                refresh();
            });
            return button;
        });
    }

    private void setDrugDto(DrugDto drugDto){
        binder.setBean(drugDto);
    }

    public void refresh(){
        List<DrugDto> drugs = drugService.getUserDrugList();
        if (drugs.isEmpty()){
            drugGrid.setVisible(false);
            emptyList.setVisible(true);
        } else {
            emptyList.setVisible(false);
            drugGrid.setVisible(true);
            drugGrid.setDrugGrid(drugs);
        }
    }

    public Grid<DrugDto> getDrugGrid() {
        return drugGrid.getDrugGrid();
    }

    public Button getAddToListButton() {
        return addToListButton;
    }
}
