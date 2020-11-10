package com.greenhouse.ServerSide;

import java.io.Serializable;

public class Conditions implements Serializable {
    private double temperature;
    private double humidity;
    private boolean watering;

    public Conditions(double temperature, double humidity, boolean watering) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.watering = watering;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public boolean isWatering() {
        return watering;
    }

    public void setWatering(boolean watering) {
        this.watering = watering;
    }
}
