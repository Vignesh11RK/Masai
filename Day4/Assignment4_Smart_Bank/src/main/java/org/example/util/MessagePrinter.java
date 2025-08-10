package org.example.util;

public class MessagePrinter {

    public static void printMessages(String...messages ){
        //this will accept variable in String Arguments
        for(String message:messages){
            System.out.println(message);
        }

    }

}
