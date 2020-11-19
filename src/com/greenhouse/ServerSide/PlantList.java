package com.greenhouse.ServerSide;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantList implements Serializable, PlantListInterface {
    // Creates instance of the plant list
    private static final PlantList instance = new PlantList();
    static int counter = 0;
    // Assign serializable ID
    private static final long serialVersionUID = -4108359164431780327L;
    public ArrayList<Plant> listOfPlants = new ArrayList<>();

    /**
     * Constructor for PlantList
     */
    private PlantList(){}

    /**
     * Getter for the instance of the PlantList
     * @return Instance of PlantList
     */
    public static PlantList getInstance() {
        return instance;
    }

    public void addPlant(Plant plant){
        try {
            listOfPlants.add(plant);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removePlant(int plantID){
        if (plantID > listOfPlants.size()){
            throw new IllegalArgumentException("ID must be smaller than list length");
        }
        else{
            listOfPlants.remove(plantID);
            System.out.println("Plant has been successfully removed!");
        }
    }

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
