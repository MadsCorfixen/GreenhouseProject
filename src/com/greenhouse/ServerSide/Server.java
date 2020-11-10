package com.greenhouse.ServerSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class Server implements Serializable{

    public static final int PORT = 6969;
    PlantList listOfPlants = new PlantList();

    public static void main(String[] args) throws Exception{
        new Server();
    }

    public Server() throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(PORT);
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
                outStream.writeObject("Plant has been added to your greenhouse :-)");
            }

            if (requestType.equals("removePlant")) {
                int plantID = (int) inStream.readObject();
                listOfPlants.removePlant(plantID);
                outStream.writeObject("Plant " + plantID + " has been removed");
            }

            if (requestType.equals("getPlants")) {
                listOfPlants.getListOfPlants();
                outStream.writeObject("Here is list of plants");
            }

            // https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java

            if (requestType.equals("exitAndSave")){
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
    }
}
