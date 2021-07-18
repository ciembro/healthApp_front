package com.ciembro.healthappfront.treatment;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;

@Route("treatments")
@Getter
public class UserTreatmentsView extends VerticalLayout {

    private SearchDrugLayout searchDrugLayout = new SearchDrugLayout(this);
    private Button backToMenuButton = new Button("Wróć do menu");
    private UserTreatmentListLayout userTreatmentListLayout = new UserTreatmentListLayout(this);
    private UserTreatmentAddForm userTreatmentAddForm = new UserTreatmentAddForm(this);
    private UserTreatmentEditForm userTreatmentEditForm = new UserTreatmentEditForm(this);

    public UserTreatmentsView(){
        goToMainViewIfNotLogged();
        userTreatmentAddForm.setVisible(false);
        userTreatmentEditForm.setVisible(false);

        backToMenuButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("/"));

        HorizontalLayout treatmentOperations = new HorizontalLayout(userTreatmentListLayout, userTreatmentAddForm,
                userTreatmentEditForm);
        treatmentOperations.setSizeFull();

        add(backToMenuButton, searchDrugLayout, treatmentOperations);
        setSizeFull();
    }

    private void goToMainViewIfNotLogged(){
        if (VaadinSession.getCurrent().getAttribute("token") == null){
            UI.getCurrent().getPage().setLocation("/");
        }
    }



}
