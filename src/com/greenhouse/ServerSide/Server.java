package com.greenhouse.ServerSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class Server {

    public static final int PORT = 6969;
    PlantList listOfPlants = new PlantList();

    public static void main(String[] args) throws Exception{
        new Server();
    }

    public Server() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket socket = serverSocket.accept();

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        String receivedPlantType = (String)inStream.readObject();
        LocalDate receivedHarvestDate = (LocalDate)inStream.readObject();
        int receivedPrefTemp = (int)inStream.readObject();

        Plant plant = new Plant(receivedPlantType, receivedHarvestDate, receivedPrefTemp);
        listOfPlants.addPlant(plant);
        outStream.writeObject("Plant has been added to your greenhouse :-)");

//        if(receivedPlant instanceof Plant) {
//            listOfPlants.addPlant(receivedPlant);
//            outStream.writeObject("Plant has been added to your greenhouse :-)");
//        }

        listOfPlants.getListOfPlants();

        serverSocket.close();
    }


}
