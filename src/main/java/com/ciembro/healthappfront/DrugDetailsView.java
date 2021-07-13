package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.DrugDto;
import com.ciembro.healthappfront.domain.SideEffectDto;
import com.ciembro.healthappfront.service.DrugService;
import com.ciembro.healthappfront.service.SideEffectService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.util.List;

@Route("details-view")
public class DrugDetailsView extends VerticalLayout implements HasUrlParameter<Long> {

    private DrugDto drugDto;
    private SideEffectService service = new SideEffectService();
    private DrugService drugService = new DrugService();
    private Grid<SideEffectDto> sideEffectGrid = new Grid<>(SideEffectDto.class);
    private Button backToListButton = new Button("Wróć do listy");
    private Button addToListButton = new Button("Dodaj");
    private Label noSideEffectsLabel = new Label("Nie dodałeś żadnych efektów ubocznych");

    public DrugDetailsView(){
        noSideEffectsLabel.setVisible(false);
        sideEffectGrid.setVisible(false);
        backToListButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("user-view"));
        removeDefaultGridColumns();
        createSideEffectGrid();
        addSideEffectGridButtons();

        if (drugDto != null){
            refresh();
        }
        add(new HorizontalLayout(backToListButton, addToListButton),
                sideEffectGrid,
                noSideEffectsLabel);
    }

    @Override
    public void setParameter(BeforeEvent event, Long drugId) {
        this.drugDto = drugService.getDrugById(drugId);
        noSideEffectsLabel.setVisible(false);
        if (drugDto != null){
            refresh();
        }
    }

    private void refresh(){
        List<SideEffectDto> sideEffects = service.getSideEffectList(drugDto.getId());
        if (sideEffects.isEmpty()){
            sideEffectGrid.setVisible(false);
            noSideEffectsLabel.setVisible(true);
        } else {
            noSideEffectsLabel.setVisible(false);
            sideEffectGrid.setItems(sideEffects);
            sideEffectGrid.setVisible(true);
        }
    }

    private void removeDefaultGridColumns(){
        sideEffectGrid.removeColumnByKey("id");
        sideEffectGrid.removeColumnByKey("username");
        sideEffectGrid.removeColumnByKey("drugId");
        sideEffectGrid.removeColumnByKey("creationDate");
        sideEffectGrid.removeColumnByKey("details");
    }

    private void createSideEffectGrid(){
        sideEffectGrid.addColumn(sideEffectDto -> {
            LocalDateTime creationDate = sideEffectDto.getCreationDate();
            return creationDate == null ? "-" : creationDate;
        }).setHeader("Data dodania");
        sideEffectGrid.addColumn(sideEffectDto -> {
            String details = sideEffectDto.getDetails();
            return details == null ? "-" : details;
        }).setHeader("Opis");
    }

    private void addSideEffectGridButtons(){
        sideEffectGrid.addComponentColumn(sideEffectDto -> {
            Button button = new Button("Edytuj");

            return button;
        });

        sideEffectGrid.addComponentColumn(sideEffectDto -> {
            Button button = new Button("Usuń");
            button.addClickListener(e -> {
                service.deleteSideEffectFromList(sideEffectDto);
                refresh();
            });
            return button;
        });
    }
}
