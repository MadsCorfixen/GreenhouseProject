package com.greenhouse.ServerSide;

import java.io.Serializable;

public class Conditions implements Serializable {
    private double temperature;
    private double humidity;
    private boolean watering;

    /**
     * The constructor for the conditions
     * @param temperature: A double describing the temperature
     * @param humidity: A double describing the humidity
     * @param watering: A boolean which is true if sprinklers are watering
     */
    public Conditions(double temperature, double humidity, boolean watering) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.watering = watering;
    }

    /**
     * A getter for the temperature
     * @return The temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * A setter for the temperature
     * @param temperature: A double to which the temperature is to be set
     * @throws IllegalArgumentException if the temperature is below freezing
     */
    public void setTemperature(double temperature) {
        if(temperature < 0){
            throw new IllegalArgumentException("Temperature must be above 0 \u2103");
        }
        else{
            this.temperature = temperature;
        }
    }

    /**
     * A getter for the humidity
     * @return The humidity
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * A setter for the humidity
     * @param humidity: A double to which the humidity is to be set
     * @throws IllegalArgumentException if the humidity is negative
     */
    public void setHumidity(double humidity) {
        if(humidity < 0){
            throw new IllegalArgumentException("Humidity must be non-negative");
        }
        else {
            this.humidity = humidity;
        }
    }

    /**
     * A getter to check whether the sprinklers are currently watering
     * @return A boolean
     */
    public boolean isWatering() {
        return watering;
    }

    /**
     * A setter to change the status of the sprinklers
     * @param watering: A boolean which determines whether the sprinklers are on (true) or off (false)
     */
    public void setWatering(boolean watering) {
        this.watering = watering;
    }
}
