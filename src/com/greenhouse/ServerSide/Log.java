package com.greenhouse.ServerSide;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Log {
    public Logger logger;
    public FileHandler fh;
    private final static String file_name = "log.txt";

    public Log() throws IOException {
        File f = new File(file_name);
        if(!f.exists()){
            f.createNewFile();
        }
        fh = new FileHandler(file_name, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }
}
