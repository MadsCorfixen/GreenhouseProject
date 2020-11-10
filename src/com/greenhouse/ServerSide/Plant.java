package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.time.LocalDate;

public class Plant implements Serializable {

    private int preferredTemperature;
    private LocalDate harvestDate;
    private String plantType;

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


    /**
     * A getter for the temperature
     * @return The preferred temperature for the plant
     */
    public int getPreferredTemperature(){
        return preferredTemperature;
    }

    /**
     * A setter for the preferred temperature
     * @param newPreferredTemperature: An integer determining the new preferred temperature
     * @throws IllegalArgumentException if the temperature is below freezing.
     */
    public void setPreferredTemperature (int newPreferredTemperature){
        if (newPreferredTemperature < 0){
            throw new IllegalArgumentException("Temperature must be above 0 degrees");
        }
        else {
            this.preferredTemperature = newPreferredTemperature;
        }
    }

    /**
     * A getter for the plant type
     * @return The plant type of the plant
     */
    public String getPlantType() {
        return plantType;
    }

    /**
     * A setter for the plant type
     * @param newPlantType The new plant type
     */
    public void setPlantType(String newPlantType) {
        this.plantType = newPlantType;
    }

    /**
     * A getter for the harvest date
     * @return the harvest date for the plant
     */
    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    /**
     * A setter for the harvest date
     * @param newHarvestDate
     * @throws IllegalArgumentException if harvest date is set in the past
     */
    public void setHarvestDate(LocalDate newHarvestDate) {
        if (newHarvestDate.compareTo(LocalDate.now()) <= 0){
            throw new IllegalArgumentException("Date is not available");
        }
        else {
            this.harvestDate = newHarvestDate;
        }
    }
}
