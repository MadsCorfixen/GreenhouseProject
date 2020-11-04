package com.greenhouse.ClientSide;

import com.greenhouse.ServerSide.Plant;
import com.greenhouse.ServerSide.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Client();
    }

    public Client() throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", Server.PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        Plant plant = new Plant("Tomato", LocalDate.of(2021, 1, 13), 20 );
        outStream.writeObject(plant);


        String receivedMessage = (String)inStream.readObject();
        System.out.println(receivedMessage);


        outStream.close();
        socket.close();

    }

}
