package com.ciembro.healthappfront.treatment;

import com.ciembro.healthappfront.dto.CreatedUserTreatmentDto;
import com.ciembro.healthappfront.service.DrugService;
import com.ciembro.healthappfront.service.UserTreatmentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserTreatmentListLayout extends VerticalLayout {

    private UserTreatmentsView parent;
    private UserTreatmentService userTreatmentService = UserTreatmentService.INSTANCE;
    private Grid<CreatedUserTreatmentDto> drugGrid = new Grid<>(CreatedUserTreatmentDto.class);

    public UserTreatmentListLayout(UserTreatmentsView parent) {
        this.parent = parent;
        drugGrid.removeAllColumns();
        drugGrid.addColumn(CreatedUserTreatmentDto::getStartedAt).setHeader("Data rozpoczęcia");
        drugGrid.addColumn
                (createdUserTreatmentDto -> createdUserTreatmentDto.getFinishedAt() == null ? "-" : createdUserTreatmentDto.getFinishedAt())
                .setHeader("Data zakończenia");
        drugGrid.addColumn
                (createdUserTreatmentDto -> createdUserTreatmentDto.getDrugDto().getTradeName())
                .setHeader("Preparat");
        drugGrid.addColumn
                (createdUserTreatmentDto -> createdUserTreatmentDto.getDrugDto().getDosage())
                .setHeader("Moc");
        drugGrid.addColumn
                (createdUserTreatmentDto -> createdUserTreatmentDto.getDrugDto().getBrand())
                .setHeader("Producent");
        setDrugGridItems();

        drugGrid.addComponentColumn(createdUserTreatmentDto -> {
            Button button = new Button("Edytuj");
            button.addClickListener(e -> {
                parent.getUserTreatmentEditForm().setVisible(true);
                parent.getUserTreatmentEditForm().setUpForm(createdUserTreatmentDto);
            });
            return button;
        });

        drugGrid.addComponentColumn(createdUserTreatmentDto -> {
            Button button = new Button("Usuń");
            button.addClickListener(e -> {
                userTreatmentService.deleteUserTreatment(createdUserTreatmentDto);
                setDrugGridItems();
            });
            return button;
        });

        drugGrid.addComponentColumn(createdUserTreatmentDto -> {
            Button button = new Button("Pobierz ulotkę");
            button.addClickListener(e -> {
                UI.getCurrent().getPage()
                        .setLocation(createdUserTreatmentDto.getDrugDto().getLeafletUrl());
            });
            return button;
        });

        drugGrid.setSizeFull();
        add(drugGrid);
        setSizeFull();
    }

    public void setDrugGridItems(){
        drugGrid.setItems(userTreatmentService.getAllUserTreatments());
    }
}
