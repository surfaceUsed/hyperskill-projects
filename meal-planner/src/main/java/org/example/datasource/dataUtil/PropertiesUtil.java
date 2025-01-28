package org.example.datasource.dataUtil;

import java.util.Properties;

public final class PropertiesUtil {

    private final static Properties PROPERTIES = new Properties();

    private PropertiesUtil() {}

    static {
        loadProperties();
    }

    private static void loadProperties() {

        PROPERTIES.put("db.url", "jdbc:postgresql://localhost:5432/meals_db");
        PROPERTIES.put("db.username", "postgres");
        PROPERTIES.put("db.password", "1111");
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
