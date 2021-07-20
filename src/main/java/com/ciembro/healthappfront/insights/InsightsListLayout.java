package com.ciembro.healthappfront.insights;

import com.ciembro.healthappfront.dto.CreatedInsightsDto;
import com.ciembro.healthappfront.dto.EmotionalStateDto;
import com.ciembro.healthappfront.service.InsightsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class InsightsListLayout extends VerticalLayout {

    private InsightsView parent;
    private InsightsService insightsService = InsightsService.INSTANCE;
    private Grid<CreatedInsightsDto> insightGrid = new Grid<>(CreatedInsightsDto.class);

    public InsightsListLayout(InsightsView parent) {
        this.parent = parent;
        setGridColumns();
        setInsightGridItems();
        insightGrid.setSizeFull();
        add(insightGrid);

        setSizeFull();
    }

    public void setInsightGridItems(){
        insightGrid.setItems(insightsService.getAllUserInsights());
    }

    private void setGridColumns(){

        insightGrid.removeAllColumns();
        insightGrid.addColumn(CreatedInsightsDto::getCreationDate).setHeader("Utworzono");
        insightGrid.addColumn(CreatedInsightsDto::getEmotions).setHeader("Emocje");
        insightGrid.addColumn(CreatedInsightsDto::getSideEffects).setHeader("Skutki uboczne");
        insightGrid.addColumn(CreatedInsightsDto::getComment).setHeader("Komentarz");
        insightGrid.addColumn(new ComponentRenderer<>(insightsDto -> {
           String url = insightsDto.getWeather().getIconUrl();
           return new Image(url, "");
        })).setHeader("Pogoda");
        insightGrid.addComponentColumn((insightsDto -> {
            Button button = new Button("Edytuj");
            button.addClickListener(e -> {
                    parent.getInsightEditForm().setVisible(true);
                    parent.getInsightEditForm().setUpForm(insightsDto);
            });
            return button;
        }));
        insightGrid.addComponentColumn((insightsDto -> {
            Button button = new Button("Usuń");
            button.addClickListener(e -> {
                insightsService.deleteInsights(insightsDto);
                setInsightGridItems();
            });

            return button;
        }));
    }
}
