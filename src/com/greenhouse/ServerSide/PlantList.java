package com.greenhouse.ServerSide;

import java.util.ArrayList;

public class PlantList {
    static int MAX_PLANTS = 69;
    ArrayList<Plant> listOfPlants = new ArrayList<>();

    public void addPlant(Plant plant){
        try {
            listOfPlants.add(plant);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // TO-DO: Lav nedenstående metode
    public void removePlant(Plant plant){
        try {
            listOfPlants.remove(plant);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getListOfPlants(){
        for (int i = 0; i < listOfPlants.size(); i++) {
            System.out.println("Plant Number: " + (i + 1));
            System.out.println(listOfPlants.get(i).getPlantType());
            System.out.println(listOfPlants.get(i).getHarvestDate());
            System.out.println(listOfPlants.get(i).getPreferredTemperature());
        }
    }
}
