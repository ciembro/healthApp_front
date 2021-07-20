package com.ciembro.healthappfront.insights;

import com.ciembro.healthappfront.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;


@Route("insights")
@Getter
public class InsightsView extends VerticalLayout {

    private H1 header = new H1("Dziennik spostrzeżeń");
    private InsightsAddForm insightsAddForm = new InsightsAddForm(this);
    private InsightsListLayout insightsListLayout = new InsightsListLayout(this);
    private InsightEditForm insightEditForm = new InsightEditForm(this);

    public InsightsView() {
        goToMainViewIfNotLogged();
        insightEditForm.setVisible(false);
        add(header, insightsAddForm, insightEditForm, insightsListLayout);
        setSizeFull();
    }

    private void goToMainViewIfNotLogged(){
        if (VaadinSession.getCurrent().getAttribute("token") == null){
            UI.getCurrent().getPage().setLocation("/");
        }
    }
}
