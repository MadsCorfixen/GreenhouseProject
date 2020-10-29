package com.greenhouse.ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(6969);
            Socket soc = ss.accept();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
