package org.example.util;

import org.example.model.DatabaseProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static Connection CONNECTION;

    private ConnectionManager() {}

    public static void initConnection(DatabaseProperties databaseProperties) {
        try {

            CONNECTION = DriverManager.getConnection(databaseProperties.getDatabaseUrl(),
                    databaseProperties.getUserName(),
                    databaseProperties.getPassword());

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

    public static void closeConnection() {

        try {
            getConnection().close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}