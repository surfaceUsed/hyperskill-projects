package org.example.logic;

public class DatabaseProperties {

    private final String databaseUrl;
    private final String username;
    private final String password;

    public DatabaseProperties(String[] properties) {

        if (properties.length != 3) {
            throw new RuntimeException("Command-line inputs malformed");
        }

        this.databaseUrl = properties[0];
        this.username = properties[1];
        this.password = properties[2];
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
