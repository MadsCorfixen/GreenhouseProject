package com.greenhouse;

import java.time.LocalDate;

public class Log {
    private double temperature;
    private LocalDate dateTime;
    private double groundHumidity;

    public Double getTemp() {
        return temperature;
    }

    public void setTemp( Double newTemp) {
        if (NewTemp < 0)
            throw new IllegalArgumentException("Temperature cant be set to negative degrees");
        else
            temperature = newTemp;
    }
}
