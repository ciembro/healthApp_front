package com.ciembro.healthappfront.insights;

import com.ciembro.healthappfront.dto.EmotionalStateDto;
import com.ciembro.healthappfront.dto.SideEffectDto;
import com.ciembro.healthappfront.service.EmotionalStateService;
import com.ciembro.healthappfront.service.InsightsService;
import com.ciembro.healthappfront.service.SideEffectService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public abstract class InsightsForm extends VerticalLayout {

    protected InsightsView parent;
    protected EmotionalStateService emotionalStateService = EmotionalStateService.INSTANCE;
    protected SideEffectService sideEffectService = SideEffectService.INSTANCE;
    protected Button saveButton = new Button("Zapisz");
    protected Button cancelButton = new Button("Anuluj");
    protected TextArea comment = new TextArea("Komentarz");
    protected MultiSelectListBox<EmotionalStateDto> emotions = new MultiSelectListBox<>();
    protected MultiSelectListBox<SideEffectDto> sideEffects = new MultiSelectListBox<>();
    protected HorizontalLayout formFields = new HorizontalLayout(emotions, sideEffects, comment);
    protected HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);

    public InsightsForm(InsightsView parent) {
        this.parent = parent;

        emotions.setItems(emotionalStateService.getEmotionsList());
        sideEffects.setItems(sideEffectService.getSideEffectList());

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
