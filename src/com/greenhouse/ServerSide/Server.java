package com.greenhouse.ServerSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class Server implements Serializable{

    public static final int PORT = 6969;
    PlantList listOfPlants = new PlantList();
    int minTemp = -20;
    int maxTemp = 60;
    double todayTemp = Math.random() * (maxTemp - minTemp + 1) + minTemp;
    int minHum = 0;
    int maxHum = 100;
    double todayHum = Math.random() * (maxHum - minHum + 1) + minHum;
    public Log add_log = new Log();

    Conditions currentConditions = new Conditions(todayTemp, todayHum, false);

    public static void main(String[] args) throws Exception{
        new Server();
    }

    public Server() throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(PORT);
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

        while (true) {
            Socket socket = server.accept();

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            String requestType = (String)inStream.readObject();
            if (requestType.equals("addPlant")) {
                String receivedPlantType = (String) inStream.readObject();
                LocalDate receivedHarvestDate = (LocalDate) inStream.readObject();
                int receivedPrefTemp = (int) inStream.readObject();

                Plant plant = new Plant(receivedPlantType, receivedHarvestDate, receivedPrefTemp);
                listOfPlants.addPlant(plant);

                try {
                    add_log.logger.info("Plant with Type: " + receivedPlantType + ", Harvest Date: " + receivedHarvestDate
                    + ", Preferred Temp: " + receivedPrefTemp + " has been added to plant list");
                } catch (Exception e) {
                    System.out.println("Log has not been found");
                }

                outStream.writeObject("Plant has been added to your greenhouse :-)");
            }

            if (requestType.equals("removePlant")) {
                int plantID = (int) inStream.readObject();

                try {
                    add_log.logger.info("PlantID: " + plantID + " has been removed from: " + listOfPlants.getListOfPlants().toString());
                } catch (Exception e) {
                    System.out.println("Log has not been found");
                }

                listOfPlants.removePlant(plantID);
                outStream.writeObject("Plant " + plantID + " has been removed.");
            }

            if (requestType.equals("getPlants")) {
                outStream.writeObject(listOfPlants.getListOfPlants().toString());
            }

            // Conditions
            if (requestType.equals("changeTemperature")){
                double temperature = (double) inStream.readObject();
                currentConditions.setTemperature(temperature);
                outStream.writeObject("Temperature was set to " + currentConditions.getTemperature() + ".");
            }

            if (requestType.equals("changeHumidity")){
                double humidity = (double) inStream.readObject();
                currentConditions.setHumidity(humidity);
                outStream.writeObject("Humidity was set to " + currentConditions.getHumidity() + ".");
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
                currentConditions.setWatering(true);
                outStream.writeObject("Watering was started.");
            }

            if (requestType.equals("stopWatering")) {
                currentConditions.setWatering(false);
                outStream.writeObject("Watering was stopped.");
            }


            // Exit and Save
            if (requestType.equals("exitAndSave")){
                try {
                    add_log.logger.info("GUI EXIT --- Changes have been saved");
                } catch (Exception e) {
                    System.out.println("Log has not been found");
                }

                try {
                    FileOutputStream fileOut = new FileOutputStream("PlantList.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(listOfPlants);
                    out.close();
                    fileOut.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                outStream.writeObject("Server Closing");
                break;
            }
        }
        server.close();
        try {
            add_log.logger.info("Server Closed");
        } catch (Exception e) {
            System.out.println("Log has not been found");
        }
    }
}
