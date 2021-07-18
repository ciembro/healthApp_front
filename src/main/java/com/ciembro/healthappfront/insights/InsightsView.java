package com.ciembro.healthappfront.insights;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.Getter;


@Route("insights")
@Getter
public class InsightsView extends VerticalLayout {

    private InsightsAddForm insightsAddForm = new InsightsAddForm(this);
    private InsightsListLayout insightsListLayout = new InsightsListLayout(this);
    private InsightEditForm insightEditForm = new InsightEditForm(this);

    public InsightsView() {

        insightEditForm.setVisible(false);
        add(insightsAddForm, insightEditForm, insightsListLayout);

        setSizeFull();



    }
}
