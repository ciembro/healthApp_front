package com.ciembro.healthappfront.insights;

import com.ciembro.healthappfront.dto.CreatedInsightsDto;
import com.ciembro.healthappfront.service.InsightsService;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.server.VaadinSession;

public class InsightEditForm extends InsightsForm{

    private InsightsService insightsService = InsightsService.INSTANCE;
    private Binder<CreatedInsightsDto> binder = new Binder<>(CreatedInsightsDto.class);
    private CreatedInsightsDto createdInsightsDto = new CreatedInsightsDto();

    public InsightEditForm(InsightsView parent) {
        super(parent);
        binder.bindInstanceFields(this);
        binder.setBean(createdInsightsDto);
    }

    public void setUpForm(CreatedInsightsDto insightsDto){
        binder.setBean(insightsDto);
        System.out.println(insightsDto);
    }

    @Override
    protected void save() {
        createdInsightsDto = binder.getBean();
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        createdInsightsDto.setUsername(username);
        insightsService.updateInsights(createdInsightsDto);

        setVisible(false);
        clearForm();
        parent.getInsightsListLayout().setInsightGridItems();
    }

    @Override
    protected void cancel() {
        setVisible(false);
        clearForm();
    }
}
