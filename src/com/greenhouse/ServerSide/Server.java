package com.greenhouse.ServerSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

        Plant receivedPlant = (Plant)inStream.readObject();

        listOfPlants.addPlant(receivedPlant);
        listOfPlants.getListOfPlants();

        if(receivedPlant instanceof Plant) {
            outStream.writeObject("Plant has been added to your greenhouse :-)");
        }

        serverSocket.close();
    }


}
