package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantList implements Serializable {
    static int counter = 0;
    private static final long serialVersionUID = -4108359164431780327L;
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
        if (plantID > listOfPlants.size()){
            throw new IllegalArgumentException("ID must be smaller than list length");
        }
        else if (plantID < 0) {
            throw new IllegalArgumentException("ID must be bigger than 0");
        }
        else{
            listOfPlants.remove(plantID);
            System.out.println("Plant has been successfully removed!");
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
    public int getIfHarvestable(){
        for (int i = 0; i < listOfPlants.size(); i++) {
            if (listOfPlants.get(i).getHarvestDate().compareTo(LocalDate.now()) <= 0) {
                counter += 1;
            }
        }
        return counter;
    }
}
