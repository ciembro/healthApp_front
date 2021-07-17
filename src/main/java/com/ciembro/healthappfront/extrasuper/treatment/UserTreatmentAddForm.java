package com.ciembro.healthappfront.extrasuper.treatment;

import com.ciembro.healthappfront.extrasuper.dto.DrugDto;
import com.ciembro.healthappfront.extrasuper.dto.UserTreatmentDto;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;

public class UserTreatmentAddForm extends UserTreatmentForm {

    private Binder<UserTreatmentDto> binder = new Binder<>(UserTreatmentDto.class);
    private UserTreatmentDto userTreatmentDto = new UserTreatmentDto();

    public UserTreatmentAddForm(UserTreatmentsView parent) {
        super(parent);
        setHeader("Dodaj szczegóły leczenia");
        binder.bindInstanceFields(this);
        binder.setBean(userTreatmentDto);
    }

    @Override
    public void save() {
        userTreatmentDto = binder.getBean();
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        userTreatmentDto.setUsername(username);

        //TODO service
        parent.getUserTreatmentListLayout().setDrugGridItems();
    }

    public void setUpForm(DrugDto drugDto){
        userTreatmentDto.setDrugDto(drugDto);
        commonName.setValue(drugDto.getCommonName());
        tradeName.setValue(drugDto.getTradeName());
        dose.setValue(drugDto.getDose());
        brand.setValue(drugDto.getBrand());
        startedAt.setValue(LocalDate.now());
    }
}
