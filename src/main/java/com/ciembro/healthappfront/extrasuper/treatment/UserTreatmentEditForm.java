package com.ciembro.healthappfront.extrasuper.treatment;

import com.ciembro.healthappfront.extrasuper.dto.CreatedUserTreatmentDto;
import com.ciembro.healthappfront.extrasuper.dto.DrugDto;
import com.vaadin.flow.data.binder.Binder;

public class UserTreatmentEditForm extends UserTreatmentForm{

    private Binder<CreatedUserTreatmentDto> binder = new Binder<>(CreatedUserTreatmentDto.class);
    private CreatedUserTreatmentDto userTreatmentDto = new CreatedUserTreatmentDto();

    public UserTreatmentEditForm(UserTreatmentsView parent) {
        super(parent);
        setHeader("Edytuj szczegóły leczenia");
        binder.bindInstanceFields(this);
    }

    @Override
    public void save() {
        userTreatmentDto = binder.getBean();
        System.out.println(userTreatmentDto);
        //TODO service
        parent.getUserTreatmentListLayout().setDrugGridItems();
    }

    public void setUpForm(CreatedUserTreatmentDto treatmentDto){
        userTreatmentDto = treatmentDto;
        binder.setBean(userTreatmentDto);

        //TODO mozna sprobowac zrobic Binder<DrugDto>
        DrugDto drugDto = treatmentDto.getDrugDto();
        commonName.setValue(drugDto.getCommonName());
        tradeName.setValue(drugDto.getTradeName());
        dose.setValue(drugDto.getDose());
        brand.setValue(drugDto.getBrand());
        startedAt.setValue(treatmentDto.getStartedAt());
        finishedAt.setValue(treatmentDto.getFinishedAt());
    }
}
