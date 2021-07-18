package com.ciembro.healthappfront.insights;

import com.ciembro.healthappfront.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;


@Route("insights")
@Getter
public class InsightsView extends VerticalLayout {

    private InsightsAddForm insightsAddForm = new InsightsAddForm(this);
    private InsightsListLayout insightsListLayout = new InsightsListLayout(this);
    private InsightEditForm insightEditForm = new InsightEditForm(this);

    public InsightsView() {
        goToMainViewIfNotLogged();
        insightEditForm.setVisible(false);
        add(insightsAddForm, insightEditForm, insightsListLayout);
        setSizeFull();
    }

    private void goToMainViewIfNotLogged(){
        if (VaadinSession.getCurrent().getAttribute("token") == null){
            UI.getCurrent().getPage().setLocation("/");
        }
    }
}
