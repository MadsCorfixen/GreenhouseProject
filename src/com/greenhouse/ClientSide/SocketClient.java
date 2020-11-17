package com.greenhouse.ClientSide;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class SocketClient {
    //socket cliient port on which it will listen
    private static final int PORT = 6969;

    // craete a constructor that takes requestType, plantType, arvestDate and prefTemp
    // and throws IOException and ClassNotFoundException exceptions
    public SocketClient(String requestType, String plantType, LocalDate harvestDate, int prefTemp) throws IOException, ClassNotFoundException {

        //establish socket connection to server
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        //write to socket using ObjectOutputStream
        outStream.writeObject(requestType);
        outStream.writeObject(plantType);
        outStream.writeObject(harvestDate);
        outStream.writeObject(prefTemp);

        //receive message to server
        //todo
        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        // Close our streams
        outStream.close();
        socket.close();
    }

    // craete a constructor that takes requestType and plantID
    // and throws IOException and ClassNotFoundException exceptions
    public SocketClient(String requestType, int plantID) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        //write to socket using ObjectOutputStream
        outStream.writeObject(requestType);
        outStream.writeObject(plantID);

        //receive message to server
        //todo
        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        // Close our streams
        outStream.close();
        socket.close();
    }

    // craete a constructor that takes requestType
    // and throws IOException and ClassNotFoundException exceptions
    public SocketClient(String requestType) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        //write to socket using ObjectOutputStream
        outStream.writeObject(requestType);

        //receive message to server
        String receivedMessage = (String)inStream.readObject();

        System.out.println(receivedMessage);

        // print out the result we got back from the server
        //todo
        if(receivedMessage.equals("Current Conditions:")){
            String receivedMessage1 = (String)inStream.readObject();
            System.out.println("Temperature: " + Math.round(Double.parseDouble(receivedMessage1) * 100.0) / 100.0 + " \u2103");
            String receivedMessage2 = (String)inStream.readObject();
            System.out.println("Humidity: " + Math.round(Double.parseDouble(receivedMessage2) * 100.0) / 100.0 + "%");
            String receivedMessage3 = (String)inStream.readObject();
            System.out.println("Is watering? " + receivedMessage3);
        }


        // Read data from the server until we finish reading the document or client closes the connection
        if(receivedMessage.equals("Here is the list:")){
            File receivedFile = (File)inStream.readObject();
            Scanner reader = new Scanner(receivedFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            // close the reader, and return the results as a String
            reader.close();
        }

        // Close our streams
        outStream.close();
        socket.close();
    }

    public SocketClient(String requestType, double condition) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        //write to socket using ObjectOutputStream
        outStream.writeObject(requestType);
        outStream.writeObject(condition);

        //receive message to server
        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        // Close our streams
        outStream.close();
        socket.close();
    }
}
