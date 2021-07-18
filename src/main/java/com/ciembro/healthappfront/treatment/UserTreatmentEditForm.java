package com.ciembro.healthappfront.treatment;

import com.ciembro.healthappfront.dto.CreatedUserTreatmentDto;
import com.ciembro.healthappfront.dto.DrugDto;
import com.ciembro.healthappfront.service.UserTreatmentService;
import com.vaadin.flow.data.binder.Binder;

public class UserTreatmentEditForm extends UserTreatmentForm{

    private Binder<CreatedUserTreatmentDto> binder = new Binder<>(CreatedUserTreatmentDto.class);
    private CreatedUserTreatmentDto userTreatmentDto = new CreatedUserTreatmentDto();
    private UserTreatmentService userTreatmentService = UserTreatmentService.INSTANCE;

    public UserTreatmentEditForm(UserTreatmentsView parent) {
        super(parent);
        setHeader("Edytuj szczegóły leczenia");
        binder.bindInstanceFields(this);
    }

    @Override
    public void save() {
        userTreatmentDto = binder.getBean();
        if (userTreatmentDto.getStartedAt().isBefore(userTreatmentDto.getFinishedAt())){
            userTreatmentService.updateUserTreatment(userTreatmentDto);
            parent.getUserTreatmentListLayout().setDrugGridItems();
            setVisible(false);
        }
    }

    public void setUpForm(CreatedUserTreatmentDto treatmentDto){
        userTreatmentDto = treatmentDto;
        binder.setBean(userTreatmentDto);

        //TODO mozna sprobowac zrobic Binder<DrugDto>
        DrugDto drugDto = treatmentDto.getDrugDto();
        internationalName.setValue(drugDto.getInternationalName());
        tradeName.setValue(drugDto.getTradeName());
        dose.setValue(drugDto.getDose());
        brand.setValue(drugDto.getBrand());
        startedAt.setValue(treatmentDto.getStartedAt());
        finishedAt.setValue(treatmentDto.getFinishedAt());
    }
}
