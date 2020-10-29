package com.greenhouse.ClientSide;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args){
        try{
            Socket soc = new Socket("localhost", 6969);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
