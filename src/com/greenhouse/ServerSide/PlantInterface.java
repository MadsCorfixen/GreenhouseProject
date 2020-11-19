package com.greenhouse.ServerSide;

import java.time.LocalDate;

/**
 *
 */
public interface PlantInterface {
    /**
     * A getter for the temperature
     * @return The preferred temperature for the plant
     */
    int getPreferredTemperature();

    /**
     * A setter for the preferred temperature
     * @param newPreferredTemperature: An integer determining the new preferred temperature
     * @throws IllegalArgumentException if the temperature is below freezing.
     */
    void setPreferredTemperature (int newPreferredTemperature);

    /**
     * A getter for the plant type
     * @return The plant type of the plant
     */
    String getPlantType();

    /**
     * A setter for the plant type
     * @param newPlantType The new plant type
     */
    void setPlantType(String newPlantType);

    /**
     * A getter for the harvest date
     * @return the harvest date for the plant
     */
    LocalDate getHarvestDate();

    /**
     * A setter for the harvest date
     * @param newHarvestDate The new harvest date
     * @throws IllegalArgumentException if harvest date is set in the past
     */
    void setHarvestDate(LocalDate newHarvestDate);
}
