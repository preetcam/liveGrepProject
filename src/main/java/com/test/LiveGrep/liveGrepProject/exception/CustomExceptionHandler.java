package com.test.LiveGrep.liveGrepProject.exception;

public class CustomExceptionHandler {

    public static void handle(Exception e) {
        if (e instanceof InvalidParameterException) {
            System.err.println(e.getMessage());
        } else {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        System.exit(1);
    }
}
