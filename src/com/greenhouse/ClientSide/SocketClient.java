package com.greenhouse.ClientSide;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class SocketClient {
    private static final int PORT = 6969;

    public SocketClient(String requestType, String plantType, LocalDate harvestDate, int prefTemp) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        outStream.writeObject(requestType);
        outStream.writeObject(plantType);
        outStream.writeObject(harvestDate);
        outStream.writeObject(prefTemp);

        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        outStream.close();
        socket.close();
    }

    public SocketClient(String requestType, int plantID) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        outStream.writeObject(requestType);
        outStream.writeObject(plantID);

        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        outStream.close();
        socket.close();
    }

    public SocketClient(String requestType) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        outStream.writeObject(requestType);

        String receivedMessage = (String)inStream.readObject();

        System.out.println(receivedMessage);

        if(receivedMessage.equals("Current Conditions:")){
            String receivedMessage1 = (String)inStream.readObject();
            System.out.println("Temperature: " + Math.round(Double.parseDouble(receivedMessage1) * 100.0) / 100.0 + " \u2103");
            String receivedMessage2 = (String)inStream.readObject();
            System.out.println("Humidity: " + Math.round(Double.parseDouble(receivedMessage2) * 100.0) / 100.0 + "%");
            String receivedMessage3 = (String)inStream.readObject();
            System.out.println("Is watering? " + receivedMessage3);
        }

        if(receivedMessage.equals("Here is the list:")){
            File receivedFile = (File)inStream.readObject();
            Scanner reader = new Scanner(receivedFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }

        outStream.close();
        socket.close();
    }

    public SocketClient(String requestType, double condition) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        outStream.writeObject(requestType);
        outStream.writeObject(condition);

        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        outStream.close();
        socket.close();
    }
}
