package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.SideEffectDto;
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

    private Long drugId;
    private SideEffectService service = new SideEffectService();
    private Grid<SideEffectDto> sideEffectGrid = new Grid<>(SideEffectDto.class);
    private Button backToListButton = new Button("Wróć do listy");
    private Button addToListButton = new Button("Dodaj");
    public DrugDetailsView(){

        backToListButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("user-view"));

        sideEffectGrid.removeColumnByKey("id");
        sideEffectGrid.removeColumnByKey("username");
        sideEffectGrid.removeColumnByKey("drugId");
        sideEffectGrid.removeColumnByKey("creationDate");
        sideEffectGrid.removeColumnByKey("details");
        add(    sideEffectGrid,
                new HorizontalLayout(backToListButton, addToListButton) );
    }

    @Override
    public void setParameter(BeforeEvent event, Long parameter) {
        drugId = parameter;
        getSideEffectList();
        refresh();


    }

    private void getSideEffectList() {
        List<SideEffectDto> sideEffects = service.getSideEffectList(drugId);
        Label emptyList = new Label("Nie dodałeś żadnych efektów ubocznych.");
        if (sideEffects.isEmpty()) {
            add(emptyList);
        } else {
            this.remove(emptyList);

            add(sideEffectGrid);
            sideEffectGrid.addColumn(sideEffectDto -> {
                LocalDateTime creationDate = sideEffectDto.getCreationDate();
                return creationDate == null ? "-" : creationDate;
            }).setHeader("Data dodania");
            sideEffectGrid.addColumn(sideEffectDto -> {
                String details = sideEffectDto.getDetails();
                return details == null ? "-" : details;
            }).setHeader("Opis");

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

    private void refresh(){

        sideEffectGrid.setItems(service.getSideEffectList(drugId));
    }
}
