package com.greenhouse.ServerSide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.*;

public class Log {
    public Logger logger;
    public FileHandler fh;
    private final static String file_name = "log.txt";

    // log function that has throw IOException
    public Log() throws IOException {

        // checking if the logging file does not exist
        // if not create new file
        File f = new File(file_name);
        if(!f.exists()){
            f.createNewFile();
        }

        //TODO delete line26
        fh = new FileHandler(file_name, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}
