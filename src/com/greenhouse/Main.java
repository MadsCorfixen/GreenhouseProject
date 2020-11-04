package com.greenhouse;

import java.time.LocalDate;
import com.greenhouse.ServerSide.*;

public class Main {

    public static void main(String[] args) {
        Plant Blomst = new Plant("Agurk", LocalDate.of(2021, 1, 13), 22);
        PlantList liste = new PlantList();
        Blomst.getHarvestDate();
        Blomst.getPlantType();
        liste.addPlant(Blomst);
        liste.getListOfPlants();
    }
}
