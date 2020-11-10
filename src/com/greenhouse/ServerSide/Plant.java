package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.time.LocalDate;

public class Plant implements Serializable {

    private int preferredTemperature;
    private LocalDate harvestDate;
    private String plantType;

    // This is the constructor for the a plant object. Inputs are from the ones above.
    public Plant (String plantType, LocalDate harvestDate, int preferredTemperature) {
        this.plantType = plantType;
        this.harvestDate = harvestDate;
        this.preferredTemperature = preferredTemperature;
    }


    //Following are getters and setters for the class Plant variables
    public int getPreferredTemperature(){
        return preferredTemperature;
    }

    public void setPreferredTemperature (int newPreferredTemperature){
        if (newPreferredTemperature < 0){
            throw new IllegalArgumentException("Temperature must be above 0 degrees");
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
