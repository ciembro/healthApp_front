package com.ciembro.healthappfront.treatment;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public abstract class UserTreatmentForm extends VerticalLayout {

    protected UserTreatmentsView parent;
    protected Header header = new Header();
    protected TextField internationalName = new TextField("Nazwa powszechnie stosowana");
    protected TextField tradeName = new TextField("Nazwa handlowa");
    protected TextField dose = new TextField("Moc");
    protected TextField brand = new TextField("Podmiot odpowiedzialny");
    protected DatePicker startedAt = new DatePicker("Data rozpoczęcia");
    protected DatePicker finishedAt = new DatePicker("Data zakończenia");

    protected Button saveButton = new Button("Zapisz");
    protected Button cancelButton = new Button("Anuluj");


    public UserTreatmentForm(UserTreatmentsView parent) {
        this.parent = parent;

        FormLayout fields = new FormLayout();
        fields.add(internationalName,
                tradeName,
                dose,
                brand,
                startedAt,
                finishedAt);

        internationalName.setReadOnly(true);
        tradeName.setReadOnly(true);
        dose.setReadOnly(true);
        brand.setReadOnly(true);

//        getStyle().set("border", "1px solid blue");

        saveButton.addClickListener(e -> save());
        cancelButton.addClickListener(e -> setVisible(false));
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
//        buttonsLayout.getStyle().set("border", "1px solid magenta");
        buttonsLayout.setWidthFull();
        add(header, fields, buttonsLayout);
        setSizeFull();
    }

    public abstract void save();

    public void setHeader(String text) {
        header.setText(text);
    }
}
