package com.ciembro.healthappfront.domain;

import com.vaadin.flow.component.grid.Grid;

import java.util.List;

public class DrugGrid {

    private Grid<DrugDto> drugGrid = new Grid<>(DrugDto.class);


    public DrugGrid(){
        removeDefaultColumns();
        createColumns();
    }

    private void removeDefaultColumns(){
        drugGrid.removeColumnByKey("id");
        drugGrid.removeColumnByKey("activeSubstance");
        drugGrid.removeColumnByKey("brand");
        drugGrid.removeColumnByKey("dose");
        drugGrid.removeColumnByKey("leafletUrl");
        drugGrid.removeColumnByKey("tradeName");
        drugGrid.removeColumnByKey("commonName");
    }

    private void createColumns(){
        drugGrid.addColumn(drugDto -> {
            String tradeName = drugDto.getTradeName();
            return tradeName == null ? "-" : tradeName;
        }).setHeader("Nazwa produktu leczniczego");
        drugGrid.addColumn(drugDto -> {
            String commonName = drugDto.getCommonName();
            return commonName == null ? "-" : commonName;
        }).setHeader("Nazwa powszechna");
        drugGrid.addColumn(drugDto -> {
            String dose = drugDto.getDose();
            return dose == null ? "-" : dose;
        }).setHeader("Moc");
        drugGrid.addColumn(drugDto -> {
            String brand = drugDto.getBrand();
            return brand == null ? "-" : brand;
        }).setHeader("Podmiot odpowiedzialny");
    }

    public Grid<DrugDto> getDrugGrid() {
        return drugGrid;
    }

    public void setDrugGrid(List<DrugDto> drugList) {
        drugGrid.setItems(drugList);
    }

    public void setVisible(boolean isVisible){
        drugGrid.setVisible(isVisible);
    }
}
