package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.util.ArrayList;

public class PlantList implements Serializable {
    static int MAX_PLANTS = 69;
    public ArrayList<Plant> listOfPlants = new ArrayList<>();

    // A method to add a plant object to the arraylist
    public void addPlant(Plant plant){
        try {
            listOfPlants.add(plant);
            System.out.println("Plant has been added to server");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Method to remove a plant objet from the arraylist
    public void removePlant(int plantID){
        try {
            listOfPlants.remove(plantID);
            System.out.println("Plant has been successfully removed!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Method that utilizes a for loop to print each attribute for every plant object in the arraylist
    public Object getListOfPlants(){
        for (int i = 0; i < listOfPlants.size(); i++) {
            System.out.println("ID of plant: " + i);
            System.out.println(listOfPlants.get(i).getPlantType());
            System.out.println(listOfPlants.get(i).getHarvestDate());
            System.out.println(listOfPlants.get(i).getPreferredTemperature());
        }
        return null;
    }
}
