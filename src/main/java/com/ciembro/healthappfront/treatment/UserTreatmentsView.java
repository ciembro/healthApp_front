package com.ciembro.healthappfront.treatment;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.Getter;

@Route("treatments")
@Getter
public class UserTreatmentsView extends VerticalLayout {

    private SearchDrugLayout searchDrugLayout = new SearchDrugLayout(this);
    private UserTreatmentListLayout userTreatmentListLayout = new UserTreatmentListLayout(this);
    private UserTreatmentAddForm userTreatmentAddForm = new UserTreatmentAddForm(this);
    private UserTreatmentEditForm userTreatmentEditForm = new UserTreatmentEditForm(this);

    public UserTreatmentsView(){
        userTreatmentAddForm.setVisible(false);
        userTreatmentEditForm.setVisible(false);

        HorizontalLayout treatmentOperations = new HorizontalLayout(userTreatmentListLayout, userTreatmentAddForm,
                userTreatmentEditForm);
        treatmentOperations.setSizeFull();

        add(searchDrugLayout, treatmentOperations);
        setSizeFull();

    }



}
