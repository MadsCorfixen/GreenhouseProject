package com.greenhouse.ClientSide;

import com.greenhouse.ServerSide.Plant;
import com.greenhouse.ServerSide.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class Client {

    public Client(String plantType, LocalDate harvestDate, int prefTemp) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", Server.PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        Plant plant = new Plant(plantType, harvestDate, prefTemp);
        outStream.writeObject(plant);


        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);


        outStream.close();
        socket.close();

    }

}
