package com.greenhouse.ServerSide;

import java.util.List;

public interface PlantListInterface {
    /**
     * A method to add a plant object to the arraylist
     * @param plant: Plant, object to be added to list of plants
     */
    void addPlant(Plant plant);

    /**
     * Method to remove a plant objet from the list of plants
     * @param plantID: int, plantID
     */
    void removePlant(int plantID);

    /**
     * Method that utilizes a for loop to print each attribute for every plant object in the list of plants
     * @return list of plants
     */
    List getListOfPlants();

    /**
     * Method to know if plants are ready for harvest.
     * @return int, 0 if no plants ready, > 0 if at least one plant ready.
     */
    int getIfHarvestable();
}
