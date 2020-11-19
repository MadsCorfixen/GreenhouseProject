package com.greenhouse.ServerSide;

import java.io.Serializable;

public class Conditions implements Serializable {
    private double temperature;
    private double humidity;
    private boolean watering;

    /**
     * The constructor for the conditions
     * @param temperature: double, describing the temperature
     * @param humidity: double, describing the humidity
     * @param watering: boolean, which is true if sprinklers are watering
     */
    public Conditions(double temperature, double humidity, boolean watering) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.watering = watering;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if(temperature < 0 | temperature > 60){
            throw new IllegalArgumentException("Temperature must be between 0 \u2103 and 60 \u2103");
        }
        else{
            this.temperature = temperature;
        }
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        if(humidity < 0 | humidity > 100){
            throw new IllegalArgumentException("Humidity must be non-negative and below 100%");
        }
        else {
            this.humidity = humidity;
        }
    }

    public boolean isWatering() {
        return watering;
    }

    public void setWatering(boolean watering) {
        this.watering = watering;
    }
}
