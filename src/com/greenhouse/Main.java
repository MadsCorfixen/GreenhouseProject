package com.greenhouse;

import java.time.LocalDate;
import com.greenhouse.ServerSide.*;

public class Main {

    public static void main(String[] args) {
        Plant Blomst = new Plant(24, LocalDate.of(2021, 1, 13), "agurk");
        PlantList liste = new PlantList();
        Blomst.getHarvestDate();
        Blomst.getPlantType();
        liste.addPlant(Blomst);
        liste.getListOfPlants();
    }
}
