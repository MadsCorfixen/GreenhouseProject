package com.greenhouse.ServerSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
                try {
                    listOfPlants.addPlant(plant);
                    add_log.logger.info("Plant with Type: " + receivedPlantType + ", Harvest Date: " + receivedHarvestDate
                    + ", Preferred Temp: " + receivedPrefTemp + " has been added to plant list");
                    outStream.writeObject("Plant has been added to your greenhouse :-)");
                } catch (Exception e) {
                    System.out.println("Plant was not added to your greenhouse :-(");
                }
            }

            if (requestType.equals("removePlant")) {
                int plantID = (int) inStream.readObject();

                try {
                    add_log.logger.info("PlantID: " + plantID + " has been removed from: " + listOfPlants.getListOfPlants().toString());
                    listOfPlants.removePlant(plantID);
                    outStream.writeObject("Plant " + plantID + " has been removed.");
                } catch (Exception e) {
                    System.out.println("Plant was not Removed");
                }
            }

            if (requestType.equals("getPlants")) {
                outStream.writeObject(listOfPlants.getListOfPlants().toString());
            }

            if (requestType.equals("getLog")) {
                File file = new File("log.txt");
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();
                outStream.writeObject("Here is list");
            }

            // Conditions
            if (requestType.equals("changeTemperature")){
                double temperature = (double) inStream.readObject();
                try {
                    currentConditions.setTemperature(temperature);
                    add_log.logger.info("Temperature was set to: " + temperature);
                    outStream.writeObject("Temperature was set to " + currentConditions.getTemperature() + ".");
                } catch (Exception e) {
                    System.out.println("Temperature was not set");
                }
            }

            if (requestType.equals("changeHumidity")){
                double humidity = (double) inStream.readObject();
                try {
                    currentConditions.setHumidity(humidity);
                    add_log.logger.info("Humidity was set to: " + humidity);
                    outStream.writeObject("Humidity was set to " + currentConditions.getHumidity() + ".");
                } catch (Exception e) {
                    System.out.println("Humidity was not set");
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
                    outStream.writeObject("Watering was started.");
                } catch (Exception e) {
                    System.out.println("Watering was not started");
                    outStream.writeObject("Watering was not started.");
                }
            }

            if (requestType.equals("stopWatering")) {
                try {
                    currentConditions.setWatering(true);
                    add_log.logger.warning("The Greenhouse has begun watering");
                    outStream.writeObject("Watering was stopped.");
                } catch (Exception e) {
                    System.out.println("Watering was not started");
                }
            }


            // Exit and Save
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
        server.close();
        try {
            add_log.logger.info("Server Closed");
        } catch (Exception e) {
            System.out.println("Log has not been found");
        }
    }
}
