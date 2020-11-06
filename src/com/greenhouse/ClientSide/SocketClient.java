package com.greenhouse.ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class SocketClient {

    private static final int PORT = 6969;

    Socket socket = new Socket("localhost", PORT);

    ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

    public SocketClient(String plantType, LocalDate harvestDate, int prefTemp) throws IOException, ClassNotFoundException {

        outStream.writeObject(plantType);
        outStream.writeObject(harvestDate);
        outStream.writeObject(prefTemp);

        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        outStream.close();
        socket.close();
    }
    public SocketClient(int plantID) throws IOException {
        System.out.println(plantID);
    }
}
