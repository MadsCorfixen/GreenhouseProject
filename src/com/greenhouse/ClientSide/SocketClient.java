package com.greenhouse.ClientSide;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Scanner;

public class SocketClient {
    // Socket client port on which it will listen
    private static final int PORT = 6969;

    /**
     * Constructor for the SocketClient, acting as a socket and controller.
     *
     * @param requestType: String, coming from AlterPlantWindow when addPlant is called then passed to the Server.
     * @param plantType: String, Plant type being passed to the server.
     * @param harvestDate: LocalDate, Preferred harvest date.
     * @param prefTemp: int, Preferred temperature.
     * @throws IOException: Input/Output Exception, invalid argument.
     * @throws ClassNotFoundException: Class not found, invalid class.
     */

    public SocketClient(String requestType, String plantType, LocalDate harvestDate, int prefTemp) throws IOException, ClassNotFoundException {

        // Establish socket connection to server
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        // Write to socket using ObjectOutputStream
        outStream.writeObject(requestType);
        outStream.writeObject(plantType);
        outStream.writeObject(harvestDate);
        outStream.writeObject(prefTemp);

        // Receive message from server
        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        // Close our streams
        outStream.close();
        socket.close();
    }

    /**
     * Constructor for the SocketClient, acting as a socket and controller.
     *
     * @param requestType: String, coming from AlterPlantWindow when removePlant is called then passed to the Server.
     * @param plantID: int, ID of plant to be harvested.
     * @throws IOException: Input/Output Exception, invalid argument.
     * @throws ClassNotFoundException: Class not found, invalid class.
     */
    public SocketClient(String requestType, int plantID) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        // Write to socket using ObjectOutputStream
        outStream.writeObject(requestType);
        outStream.writeObject(plantID);

        // Receive message from server
        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        // Close our streams
        outStream.close();
        socket.close();
    }

    /**
     * Constructor for the SocketClient, acting as a socket and controller.
     *
     * @param requestType: String, Request coming from GUI then passed to the Server or from server to client.
     * @throws IOException: Input/Output Exception, invalid argument.
     * @throws ClassNotFoundException: Class not found, invalid class.
     */
    public SocketClient(String requestType) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        // Write to socket using ObjectOutputStream
        outStream.writeObject(requestType);

        // Receive message from server
        String receivedMessage = (String)inStream.readObject();

        System.out.println(receivedMessage);

        // Print out the result we got back from the server
        if(receivedMessage.equals("Current Conditions:")){
            String receivedMessage1 = (String)inStream.readObject();
            System.out.println("Temperature: " + Math.round(Double.parseDouble(receivedMessage1) * 100.0) / 100.0 + " \u2103");
            String receivedMessage2 = (String)inStream.readObject();
            System.out.println("Humidity: " + Math.round(Double.parseDouble(receivedMessage2) * 100.0) / 100.0 + "%");
            String receivedMessage3 = (String)inStream.readObject();
            System.out.println("Is watering? " + receivedMessage3);
        }


        // Read data from the server until we finish reading the document
        if(receivedMessage.equals("Here is the list:")){
            File receivedFile = (File)inStream.readObject();
            Scanner reader = new Scanner(receivedFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            // Close the reader, and return the results as a String
            reader.close();
        }

        // Close our streams
        outStream.close();
        socket.close();
    }

    /**
     * Constructor for the SocketClient, acting as a socket and controller.
     * @param requestType: String, Request coming from AlterConditionWindow then passed to the Server.
     * @param condition: double, Changes the conditions on the server.
     * @throws IOException: Input/Output Exception, invalid argument.
     * @throws ClassNotFoundException: Class not found, invalid class.
     */
    public SocketClient(String requestType, double condition) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", PORT);

        // Create input and output streams to read from and write to the server
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        // Write to socket using ObjectOutputStream
        outStream.writeObject(requestType);
        outStream.writeObject(condition);

        // Receive message from server
        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);

        // Close our streams
        outStream.close();
        socket.close();
    }
}
