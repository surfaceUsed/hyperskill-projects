package org.example.datasource.dataUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Utility class for creating a unique ID for every new meal-entry to the database.
 *
 */

public final class MealIDGenerator {

    private static final Connection CONNECTION = ConnectionManager.getConnection();

    private static int mealID;

    private MealIDGenerator() {}

    static {
        loadMealID();
    }

    private static void loadMealID() {
        String getMealID = "SELECT MAX(meal_id) AS max_meal_id " +
                "FROM meals";

        try (PreparedStatement prep = CONNECTION.prepareStatement(getMealID);
             ResultSet res = prep.executeQuery()) {

            if (res.next()) {
                mealID = res.getInt("max_meal_id");
            } else {
                mealID = 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int generateID() {
        mealID++;
        return mealID;
    }

}
