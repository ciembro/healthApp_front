package com.ciembro.healthappfront;

import com.ciembro.healthappfront.dto.DrugDto;
import com.ciembro.healthappfront.dto.UserReportRowDto;
import com.ciembro.healthappfront.service.DrugService;
import com.ciembro.healthappfront.service.UserReportService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;
import java.util.stream.Collectors;

@Route("report")
public class UserReportLayout extends VerticalLayout {

    private Grid<UserReportRowDto> reportGrid = new Grid<>(UserReportRowDto.class);
    private UserReportService userReportService = UserReportService.INSTANCE;
    private DrugService drugService = new DrugService();
    private Button backToMenuButton = new Button("Wróć do menu");
    private H1 header = new H1("Podsumowanie");
    private Label drugFilterLabel = new Label("Filtruj po leku");

    ComboBox<DrugDto> drugFilterComboBox = new ComboBox<>();
    Button submitFilter = new Button("Filtruj");
    Button deleteFilter = new Button("Usuń filtry");
    HorizontalLayout filterButtons = new HorizontalLayout(submitFilter, deleteFilter);

    public UserReportLayout() {
        goToMainViewIfNotLogged();
        setGridColumns();
        setUpDrugFilter();
        submitFilter.addClickListener(e -> filterByDrug());
        deleteFilter.addClickListener(e -> {
            setGridItems();
        });

        HorizontalLayout filters = new HorizontalLayout(drugFilterComboBox, filterButtons);
        VerticalLayout filterLayout = new VerticalLayout(drugFilterLabel, filters);

        add(header, backToMenuButton, header, filterLayout, reportGrid);

        backToMenuButton.addClickListener(e -> {
            UI.getCurrent().getPage().setLocation("/");
        });
        setGridItems();
    }

    private void setUpDrugFilter(){
        List<DrugDto> drugFilterOptions = drugService.getDrugsByUser();
        drugFilterComboBox.setItemLabelGenerator(DrugDto::getTradeName);
        drugFilterComboBox.setItems(drugFilterOptions);
    }

    public void filterByDrug() {
        List<UserReportRowDto> filtered = userReportService.filterByDrug(drugFilterComboBox.getValue().getId());
        reportGrid.setItems(filtered);
    }

    private void setGridColumns(){
        reportGrid.removeAllColumns();

        reportGrid.addColumn(userReportRowDto ->
                userReportRowDto.getInsights().getCreationDate()).setHeader("Utworzono");
        reportGrid.addColumn(userReportRowDto -> {
            List<DrugDto> drugDtos = userReportRowDto.getDrugs();
            if (drugDtos == null || drugDtos.isEmpty()){
                return "-";
            }
            return drugDtos.stream()
                    .map(DrugDto::getTradeName)
                    .collect(Collectors.joining(", "));
        }).setHeader("Przyjmowane leki");
        reportGrid.addColumn(userReportRowDto ->
                userReportRowDto.getInsights().getSideEffects()).setHeader("Skutki uboczne");
        reportGrid.addColumn(userReportRowDto ->
                userReportRowDto.getInsights().getEmotions()).setHeader("Emocje");
        reportGrid.addColumn(userReportRowDto ->
                userReportRowDto.getInsights().getComment()).setHeader("Komentarz");

        reportGrid.addColumn(new ComponentRenderer<>(userReportRowDto -> {
            String url = userReportRowDto.getInsights().getWeather().getIconUrl();
            String alt = userReportRowDto.getInsights().getWeather().getWeatherText();
            Image weather = new Image(url, alt);
            weather.setWidth("50px");
            weather.setHeight("50px");
            return weather;
        }));
        reportGrid.addColumn(userReportRowDto ->
                userReportRowDto.getInsights().getWeather().getWeatherText()).setHeader("Pogoda");
        reportGrid.addColumns("insights.weather.temp");
    }

    private void setGridItems(){
        List<UserReportRowDto> rows = userReportService.generateReport();
        reportGrid.setItems(rows);
    }

    private void goToMainViewIfNotLogged(){
        if (VaadinSession.getCurrent().getAttribute("token") == null){
            UI.getCurrent().getPage().setLocation("/");
        }
    }

}
