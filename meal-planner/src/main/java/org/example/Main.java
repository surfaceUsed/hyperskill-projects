package org.example;

import org.example.controller.MealController;
import org.example.model.DatabaseProperties;
import org.example.util.ConnectionManager;

public class Main {

    public static void main(String[] args) {

        DatabaseProperties properties = loadDatabaseProperties(args);
        ConnectionManager.initConnection(properties); // Initializes a connection-object using the database properties.
        new MealController().run();
    }

    /**
     *
     * Loads command-line arguments, and saves them as database properties.
     *
     * It's important that the database url is first, followed by the username and password.
     */
    private static DatabaseProperties loadDatabaseProperties(String[] args) {

        return new DatabaseProperties(args);
    }
}