package com.greenhouse.ServerSide;

public interface ConditionsInterface {
    /**
     * A getter for the temperature
     * @return The temperature
     */
    double getTemperature();

    /**
     * A setter for the temperature
     * @param temperature: double, to which the temperature is to be set
     * @throws IllegalArgumentException: if the temperature is below freezing
     */
    void setTemperature(double temperature);

    /**
     * A getter for the humidity
     * @return The humidity
     */
    double getHumidity();

    /**
     * A setter for the humidity
     * @param humidity: double, to which the humidity is to be set
     * @throws IllegalArgumentException: if the humidity is negative
     */
    void setHumidity(double humidity);

    /**
     * A getter to check whether the sprinklers are currently watering
     * @return A boolean
     */
    boolean isWatering();

    /**
     * A setter to change the status of the sprinklers
     * @param watering: boolean, which determines whether the sprinklers are on (true) or off (false)
     */
    void setWatering(boolean watering);
}
