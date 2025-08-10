package org.example.util;

// import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Logger {
    default void logInfo(String message){
        //message with timestamp
        System.out.println("INFO:" + LocalDateTime.now() +"-"+message);
    }
    static String format(String message) {
        return "Formatted " + message;
    }
}
