package com.greenhouse.ServerSide;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Log {
    public Logger logger;
    public FileHandler fileHandler;
    private final static String file_name = "log.txt";

    /**
     * Constructor for the log
     * @throws IOException:
     */
    public Log() throws IOException {
        // Writes new log and appends it to the existing log in the right format
        fileHandler = new FileHandler(file_name, true);
        logger = Logger.getLogger("log");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }
}
