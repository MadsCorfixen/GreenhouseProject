package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.time.LocalDate;

public class Plant implements Serializable, PlantInterface{

    private int preferredTemperature;
    private LocalDate harvestDate;
    private String plantType;

    // Assign serializable ID
    private static final long serialVersionUID = -8783403388456769064L;

    /**
     * The constructor for a plant object
     * @param plantType: A string determining which plant type is to be creates, e.g. tomato or potato
     * @param harvestDate: A date at which the plant is fully grown
     * @param preferredTemperature: The temperature at which the plant grows optimally
     */
    public Plant (String plantType, LocalDate harvestDate, int preferredTemperature) {
        this.plantType = plantType;
        this.harvestDate = harvestDate;
        this.preferredTemperature = preferredTemperature;
    }

    public int getPreferredTemperature(){
        return preferredTemperature;
    }

    public void setPreferredTemperature (int newPreferredTemperature){
        if (newPreferredTemperature < 0 | newPreferredTemperature > 60){
            throw new IllegalArgumentException("Temperature must be above 0 \u2103 and below 60 \u2103");
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
            throw new IllegalArgumentException("Date must be in the future");
        }
        else {
            this.harvestDate = newHarvestDate;
        }
    }
}
