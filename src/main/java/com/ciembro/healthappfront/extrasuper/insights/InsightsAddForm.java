package com.ciembro.healthappfront.extrasuper.insights;

import com.ciembro.healthappfront.extrasuper.dto.InsightsDto;
import com.ciembro.healthappfront.extrasuper.service.InsightsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.Binder;

public class InsightsAddForm extends InsightsForm {

    private InsightsView parent;
    private InsightsService insightsService = new InsightsService();
    private Button addInsightButton = new Button("Dodaj");
    private Binder<InsightsDto> binder = new Binder<>(InsightsDto.class);
    private InsightsDto insightsDto = new InsightsDto();


    public InsightsAddForm(InsightsView parent) {
        super(parent);
        binder.bindInstanceFields(this);
        binder.setBean(insightsDto);
        addComponentAsFirst(addInsightButton);
        hideForm();
        addInsightButton.addClickListener(e -> showForm());
    }

    @Override
    protected void save(){
        insightsDto = binder.getBean();
        //TODO set pól
        hideForm();
        //TODO insightsService
        clearForm();
        //TODO refresh grid
    }

    @Override
    protected void cancel() {
        hideForm();
        clearForm();
    }

    private void hideForm(){
        addInsightButton.setVisible(true);
        formFields.setVisible(false);
        buttonLayout.setVisible(false);
        setHeight(null);
    }

    private void showForm(){
        addInsightButton.setVisible(false);
        formFields.setVisible(true);
        buttonLayout.setVisible(true);
    }
}
