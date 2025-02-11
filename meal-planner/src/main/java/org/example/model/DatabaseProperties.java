package org.example.model;

public class DatabaseProperties {

    private final String databaseUrl;
    private final String userName;
    private final String password;

    public DatabaseProperties(String[] properties) {

        if (properties.length != 3) {
            throw new RuntimeException("Command-line inputs malformed");
        }

        this.databaseUrl = properties[0];
        this.userName = properties[1];
        this.password = properties[2];
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
