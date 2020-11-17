package com.greenhouse.ServerSide;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Log {
    public Logger logger;
    public FileHandler fh;
    private final static String file_name = "log.txt";

    /**
     * Constructor for the log
     * @throws IOException:
     */
    public Log() throws IOException {

        // checking if the logging file does not exist
        // if not create new file
        File f = new File(file_name);
        if(!f.exists()){
            f.createNewFile();
        }

        // Writes new log and appends it to the existing log in the right format
        fh = new FileHandler(file_name, true);
        logger = Logger.getLogger("log");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}
