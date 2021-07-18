package com.ciembro.healthappfront.treatment;

import com.ciembro.healthappfront.dto.DrugDto;
import com.ciembro.healthappfront.dto.UserTreatmentDto;
import com.ciembro.healthappfront.service.UserTreatmentService;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.server.VaadinSession;

import java.time.LocalDate;

public class UserTreatmentAddForm extends UserTreatmentForm {

    private Binder<UserTreatmentDto> binder = new Binder<>(UserTreatmentDto.class);
    private UserTreatmentDto userTreatmentDto = new UserTreatmentDto();
    private UserTreatmentService userTreatmentService = UserTreatmentService.INSTANCE;

    public UserTreatmentAddForm(UserTreatmentsView parent) {
        super(parent);
        setHeader("Dodaj szczegóły leczenia");
        binder.bindInstanceFields(this);
        binder.setBean(userTreatmentDto);
    }

    @Override
    public void save() {
        userTreatmentDto = binder.getBean();
        if (startedAt.getValue().isBefore(finishedAt.getValue())){
            String username = (String) VaadinSession.getCurrent().getAttribute("username");
            userTreatmentDto.setUsername(username);
            userTreatmentService.createUserTreatment(userTreatmentDto);
            parent.getUserTreatmentListLayout().setDrugGridItems();
            setVisible(false);
        }
    }

    public void setUpForm(DrugDto drugDto){
        userTreatmentDto.setDrugDto(drugDto);

        internationalName.setValue(drugDto.getInternationalName());
        tradeName.setValue(drugDto.getTradeName());
        dose.setValue(drugDto.getDose());
        brand.setValue(drugDto.getBrand());
        startedAt.setValue(LocalDate.now());
    }
}
