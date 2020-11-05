package com.greenhouse.ClientSide;

import com.greenhouse.ClientSide.AlterPlantWindow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class Client {

    private static final int PORT = 6969;

    public Client(String plantType, LocalDate harvestDate, int prefTemp, boolean addPlant) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        if (addPlant) {
            outStream.writeObject(plantType);
            outStream.writeObject(harvestDate);
            outStream.writeObject(prefTemp);
        }

        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        outStream.close();
        socket.close();
    }
}
