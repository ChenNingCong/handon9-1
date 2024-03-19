package org.example;
import java.awt.Color;

class SingletonLogger {
    private static SingletonLogger instance;

    private SingletonLogger() {
    }

    public static SingletonLogger getInstance() {
        if (instance == null) {
            instance = new SingletonLogger();
        }
        return instance;
    }

    private void printWithColor(String message, Color color) {
        String colorCode = color == Color.RED ? "\u001B[31m" :
                color == Color.YELLOW ? "\u001B[33m" : "";
        String resetCode = "\u001B[0m";
        System.out.println(colorCode + message + resetCode);
    }

    void log_internal(LogLevel level, String message) {
        switch (level) {
            case ERROR:
                printWithColor("Error: " + message, Color.RED);
                System.exit(1); // Stop the program
                break;
            case WARNING:
                printWithColor("Warning: " + message, Color.YELLOW);
                break;
            case COMMENT:
                System.out.println("Comment: " + message);
                break;
        }
    }
    public void error(String message) {
        log_internal(LogLevel.ERROR, message);
    }
    public void warning(String message) {
        log_internal(LogLevel.WARNING, message);
    }
    public void comment(String message) {
        log_internal(LogLevel.COMMENT, message);
    }

    private enum LogLevel {
        ERROR,
        WARNING,
        COMMENT
    }
}

class StaticLogger {
    private StaticLogger() {
    }

    private static void printWithColor(String message, Color color) {
        String colorCode = color == Color.RED ? "\u001B[31m" :
                color == Color.YELLOW ? "\u001B[33m" : "";
        String resetCode = "\u001B[0m";
        System.out.println(colorCode + message + resetCode);
    }

    static void log_internal(LogLevel level, String message) {
        switch (level) {
            case ERROR:
                printWithColor("Error: " + message, Color.RED);
                System.exit(1); // Stop the program
                break;
            case WARNING:
                printWithColor("Warning: " + message, Color.YELLOW);
                break;
            case COMMENT:
                System.out.println("Comment: " + message);
                break;
        }
    }
    static public void error(String message) {
        log_internal(StaticLogger.LogLevel.ERROR, message);
    }
    static public void warning(String message) {
        log_internal(StaticLogger.LogLevel.WARNING, message);
    }
    static public void comment(String message) {
        log_internal(StaticLogger.LogLevel.COMMENT, message);
    }

    private enum LogLevel {
        ERROR,
        WARNING,
        COMMENT
    }
}

public class Main {
    public static void main(String[] args) {
        SingletonLogger logger = SingletonLogger.getInstance();
        System.out.println("Hello world!");
        logger.comment("This is a comment by singleton logger");
        logger.warning("This is a warning by singleton logger");
        StaticLogger.comment("This is a comment by static logger");
        StaticLogger.warning("This is a comment by static logger");
        logger.error("Oop! error");
        StaticLogger.error("Oop! error");
        System.out.println("Unreachable");
    }
}