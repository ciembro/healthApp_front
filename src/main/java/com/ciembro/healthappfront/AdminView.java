package com.ciembro.healthappfront;

import com.ciembro.healthappfront.service.AdminService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("admin")
public class AdminView extends VerticalLayout {

    private Label label = new Label("Aktualizacja baz danych");
    private Button drugUpdateButton = new Button("Ręczna aktualizacja bazy leków");
    private Button emotionsUpdateButton = new Button("Aktualizacja bazy stanów emocjonalnych");
    private Button sideEffectsUpdateButton = new Button("Aktualizacja bazy efektów ubocznych");
    private Button backToMenuButton = new Button("Wróć do menu");
    private AdminService adminService = AdminService.INSTANCE;

    public AdminView() {
//        goToMainViewIfNotLogged();

        backToMenuButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("/"));
        drugUpdateButton.addClickListener(e -> adminService.loadDrugsDataToDb());
        emotionsUpdateButton.addClickListener(e -> adminService.loadEmotionsToDb());
        sideEffectsUpdateButton.addClickListener(e -> adminService.loadSideEffectsToDb());

        add(label, drugUpdateButton,
                emotionsUpdateButton,
                sideEffectsUpdateButton,
                backToMenuButton);
    }

    private void goToMainViewIfNotLogged(){
        if (VaadinSession.getCurrent().getAttribute("token") == null){
            UI.getCurrent().getPage().setLocation("/");
        }
    }
}
