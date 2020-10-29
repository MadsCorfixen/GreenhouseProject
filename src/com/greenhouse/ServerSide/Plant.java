package com.greenhouse.ServerSide;

import java.time.LocalDate;

public class Plant {
    // int water; Kommer senere
    private int preferredTemperature;
    private LocalDate harvestDate;
    private String plantType;
    
    public Plant (int preferredTemperature, LocalDate harvestDate, String plantType) {
        this.preferredTemperature = preferredTemperature;
        this.harvestDate = harvestDate;
        this.plantType = plantType;
    }

    public int getPreferredTemperature(){
        return preferredTemperature;
    }

    public void setPreferredTemperature(int newPreferredTemperature){
        if (newPreferredTemperature < 0){
            throw new IllegalArgumentException("Temparature must be above 0 degrees");
        }
        else {
            this.preferredTemperature = newPreferredTemperature;
        }
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String newPlantType) {
        this.plantType = newPlantType;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate newHarvestDate) {
        if (newHarvestDate.compareTo(LocalDate.now()) <= 0){
            throw new IllegalArgumentException("Date is not available");
        }
        else {
            this.harvestDate = newHarvestDate;
        }
    }
}


