package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PlantList implements Serializable {
    static int MAX_PLANTS = 69;
    public ArrayList<Plant> listOfPlants = new ArrayList<>();

    // A method to add a plant object to the arraylist
    public void addPlant(Plant plant){
        try {
            listOfPlants.add(plant);
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
    public List getListOfPlants(){
        ArrayList<String> liste = new ArrayList<>();
        for (int i = 0; i < listOfPlants.size(); i++) {
            liste.add("ID of plant:" + String.valueOf(i));
            liste.add(String.valueOf(listOfPlants.get(i).getPlantType()));
            liste.add(String.valueOf(listOfPlants.get(i).getHarvestDate()));
            liste.add(String.valueOf(listOfPlants.get(i).getPreferredTemperature()));
        }
        return liste;
    }
}
