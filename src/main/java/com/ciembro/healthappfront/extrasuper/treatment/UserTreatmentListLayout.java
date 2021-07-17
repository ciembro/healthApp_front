package com.ciembro.healthappfront.extrasuper.treatment;

import com.ciembro.healthappfront.extrasuper.dto.CreatedUserTreatmentDto;
import com.ciembro.healthappfront.extrasuper.service.DrugService;
import com.ciembro.healthappfront.extrasuper.service.UserTreatmentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserTreatmentListLayout extends VerticalLayout {

    private UserTreatmentsView parent;
    private DrugService drugService = new DrugService();
    private UserTreatmentService userTreatmentService = new UserTreatmentService();
    private Grid<CreatedUserTreatmentDto> drugGrid = new Grid<>(CreatedUserTreatmentDto.class);

    public UserTreatmentListLayout(UserTreatmentsView parent) {
        this.parent = parent;
        drugGrid.setColumns("startedAt", "finishedAt", "drugDto.commonName", "drugDto.tradeName", "drugDto.dose",
                "drugDto.brand");
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
                //TODO service
                setDrugGridItems();
            });
            return button;
        });
        drugGrid.setSizeFull();
        add(drugGrid);
        setSizeFull();
    }

    public void setDrugGridItems(){
        drugGrid.setItems(userTreatmentService.getExampleData());
    }
}
