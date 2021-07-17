package com.ciembro.healthappfront.extrasuper.insights;

import com.ciembro.healthappfront.extrasuper.dto.EmotionalStateDto;
import com.ciembro.healthappfront.extrasuper.dto.SideEffectDto;
import com.ciembro.healthappfront.extrasuper.service.InsightsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public abstract class InsightsForm extends VerticalLayout {

    protected InsightsView parent;
    protected InsightsService insightsService = new InsightsService();
    protected Button saveButton = new Button("Zapisz");
    protected Button cancelButton = new Button("Anuluj");
    protected TextArea comment = new TextArea("Komentarz");
    protected MultiSelectListBox<EmotionalStateDto> emotions = new MultiSelectListBox<>();
    protected MultiSelectListBox<SideEffectDto> sideEffects = new MultiSelectListBox<>();
    protected HorizontalLayout formFields = new HorizontalLayout(emotions, sideEffects, comment);
    protected HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);

    public InsightsForm(InsightsView parent) {
        this.parent = parent;

        emotions.setItems(insightsService.getEmotions());
        sideEffects.setItems(insightsService.getSideEffects());

        formFields.setSizeFull();
        formFields.setJustifyContentMode(JustifyContentMode.EVENLY);

        buttonLayout.setJustifyContentMode(JustifyContentMode.END);
        buttonLayout.setSizeFull();
        add(formFields, buttonLayout);
        setSizeFull();

        saveButton.addClickListener(e -> save());
        cancelButton.addClickListener(e -> cancel());

    }

    protected abstract void save();
    protected abstract void cancel();

    protected void clearForm() {
        emotions.clear();
        sideEffects.clear();
        comment.clear();
    }


}
