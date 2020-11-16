package com.greenhouse.ServerSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Server implements Serializable{

    public static final int PORT = 6969;
    PlantList listOfPlants = PlantList.getInstance(); //Since the Plantlist is a singleton, we use .getInstance.
    int minTemp = -20;
    int maxTemp = 60;
    double todayTemp = Math.random() * (maxTemp - minTemp + 1) + minTemp; //Random simulation of todays temperature
    int minHum = 0;
    int maxHum = 100;
    double todayHum = Math.random() * (maxHum - minHum + 1) + minHum; //Random simulation of todays humidity
    public Log add_log = new Log();
    boolean alreadyExecuted = false; //Bruger vi den her?
    Conditions currentConditions = new Conditions(todayTemp, todayHum, false);

    /**
     * The main method that runs the Server constructor/method??
     * @param args
     * @throws Exception in case something goes wrong when running main method
     */
    public static void main(String[] args) throws Exception{
        new Server();
    }

    /**
     * In this method/constructor? The ServerSocket is instantiated on port 6969.
     * @throws IOException in case some input or output goes wrong in the method.
     * @throws ClassNotFoundException in case of a specific class being requested for the method cannot be found.
     */
    public Server() throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(PORT);

        //Instead of throwing, we here try to catch an exception if the add_log.logger.info fails.
        try {
            add_log.logger.info("Server Connection Open");
        } catch (Exception e) {
            System.out.println("Log has not been found");
        }

        try {
            FileInputStream fileIn = new FileInputStream("PlantList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            listOfPlants = (PlantList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("PlantList Class not found");
            return;
        }

        /**
         * while true makes sure that the ...
         *
         * Here we also declare an Object Output and Input Stream, for sending and receiving objects and strings,
         * from the Server to/from the Client. Different request types executes different things.
         */
        while (true) {
            Socket socket = server.accept();

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            //Reads the request type received through the ObjectInputStream
            String requestType = (String)inStream.readObject();

            //Check Ripeness Button
            if (requestType.equals("checkRipeness")) {
                if (listOfPlants.getIfHarvestable() > 0) {
                    outStream.writeObject("There is at least one ripe plant!");
                }
                else {
                    outStream.writeObject("There are no ripe plants!");
                }
            }

            //Alter Plants Window
            if (requestType.equals("addPlant")) {
                String receivedPlantType = (String) inStream.readObject();
                LocalDate receivedHarvestDate = (LocalDate) inStream.readObject();
                int receivedPrefTemp = (int) inStream.readObject();

                Plant plant = new Plant(receivedPlantType, receivedHarvestDate, receivedPrefTemp);
                try {
                    plant.setPlantType(receivedPlantType);
                    plant.setHarvestDate(receivedHarvestDate);
                    plant.setPreferredTemperature(receivedPrefTemp);
                    listOfPlants.addPlant(plant);
                    add_log.logger.info("Plant with Type: " + receivedPlantType + ", Harvest Date: " + receivedHarvestDate
                            + ", Preferred Temp: " + receivedPrefTemp + " has been added to plant list");
                    outStream.writeObject("Plant has been added to your greenhouse :-)");
                } catch (IllegalArgumentException e) {
                    outStream.writeObject("Plant was not added to your greenhouse :-(, either date is in the past or " +
                            "temperature outside 0 \u2103 and 60 \u2103");
                    e.printStackTrace();
                }
            }

            if (requestType.equals("removePlant")) {
                int plantID = (int) inStream.readObject();
                try {
                    listOfPlants.removePlant(plantID);
                    add_log.logger.info("PlantID: " + plantID + " has been removed from: " + listOfPlants.getListOfPlants().toString());
                    outStream.writeObject("Plant " + plantID + " has been removed.");
                } catch (IllegalArgumentException e) {
                    outStream.writeObject("Plant " + plantID + " does not exist!");
                    e.printStackTrace();
                }
            }

            //Get Plants Button
            if (requestType.equals("getPlants")) {
                outStream.writeObject(listOfPlants.getListOfPlants().toString());
            }

            //Get Log Button
            if (requestType.equals("getLog")) {
                File file = new File("log.txt");
                outStream.writeObject("Here is the list:");
                outStream.writeObject(file);
            }

            // Conditions Window
            if (requestType.equals("changeTemperature")){
                double temperature = (double) inStream.readObject();
                try {
                    currentConditions.setTemperature(temperature);
                    add_log.logger.info("Temperature was set to: " + temperature + " \u2103");
                    outStream.writeObject("Temperature was set to " + currentConditions.getTemperature() + " \u2103");
                } catch (IllegalArgumentException e) {
                    outStream.writeObject("Temperature must be between 0 \u2103 and 60 \u2103");
                    e.printStackTrace();
                }
            }

            if (requestType.equals("changeHumidity")){
                double humidity = (double) inStream.readObject();
                try {
                    currentConditions.setHumidity(humidity);
                    add_log.logger.info("Humidity was set to: " + humidity + "%");
                    outStream.writeObject("Humidity was set to " + currentConditions.getHumidity() + "%");
                } catch (Exception e) {
                    outStream.writeObject("Humidity must be between 0% and 100%");
                    e.printStackTrace();
                }
            }

            if (requestType.equals("getConditions")){
                String currentTemp = String.valueOf(currentConditions.getTemperature());
                String currentHum = String.valueOf(currentConditions.getHumidity());
                String isWatering = String.valueOf(currentConditions.isWatering());
                outStream.writeObject("Current Conditions:");
                outStream.writeObject(currentTemp);
                outStream.writeObject(currentHum);
                outStream.writeObject(isWatering);
            }

            if (requestType.equals("startWatering")) {
                try {
                    currentConditions.setWatering(true);
                    add_log.logger.warning("The Greenhouse has begun watering");
                    outStream.writeObject("Watering was started");
                } catch (Exception e) {
                    outStream.writeObject("Watering was not started");
                }
            }

            if (requestType.equals("stopWatering")) {
                try {
                    currentConditions.setWatering(true);
                    add_log.logger.warning("The Greenhouse has stopped watering");
                    outStream.writeObject("Watering was stopped");
                } catch (Exception e) {
                    outStream.writeObject("Watering was not stopped");
                }
            }

            // Exit and Save Button
            if (requestType.equals("exitAndSave")){
                try {
                    add_log.logger.info("GUI EXIT --- Changes have been saved");
                    FileOutputStream fileOut = new FileOutputStream("PlantList.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(listOfPlants);
                    out.close();
                    fileOut.close();
                    outStream.writeObject("Changes have been saved and server is closing");
                } catch (IOException i) {
                    i.printStackTrace();
                }

                break;
            }
        }

        /**
         * When the while(true) loop breaks in line 203, the server closes and we try to add to the log, that this
         * has happend.
         */
        server.close();
        try {
            add_log.logger.info("Server Closed");
        } catch (Exception e) {
            System.out.println("Log has not been found");
        }
    }
}

