package com.ciembro.healthappfront.insights;

import com.ciembro.healthappfront.dto.InsightsDto;
import com.ciembro.healthappfront.service.InsightsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.server.VaadinSession;

public class InsightsAddForm extends InsightsForm {

    private InsightsService insightsService = InsightsService.INSTANCE;
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
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        insightsDto.setUsername(username);
        insightsService.createInsights(insightsDto);
        hideForm();
        clearForm();
        parent.getInsightsListLayout().setInsightGridItems();
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
