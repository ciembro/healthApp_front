package com.ciembro.healthappfront.extrasuper.insights;

import com.ciembro.healthappfront.extrasuper.dto.CreatedInsightsDto;
import com.ciembro.healthappfront.extrasuper.service.InsightsService;
import com.vaadin.flow.data.binder.Binder;

public class InsightEditForm extends InsightsForm{

    private InsightsService insightsService = new InsightsService();
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
        //TODO set pól username

        //TODO insightsService
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
