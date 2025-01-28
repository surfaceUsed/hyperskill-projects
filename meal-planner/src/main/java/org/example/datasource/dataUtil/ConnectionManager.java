package org.example.datasource.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private ConnectionManager() {}

    public static Connection getConnection() {

        Connection con = null;

        try {

            con = DriverManager.getConnection(PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return con;
    }

    public static void closeConnection() {

        try {
            getConnection().close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}