package org.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Utility class for creating a unique ID for every new meal-entry in the database.
 */
public final class MealIDGenerator {

    private static final Connection CONNECTION = ConnectionManager.getConnection();

    // Atomic integer to track and generate new meal IDs.
    private static final AtomicInteger MEAL_ID = new AtomicInteger();

    private MealIDGenerator() {}

    // Static block to initialize the meal ID counter.
    static {
        loadMealID();
    }


    /**
     * Loads the highest existing meal ID from the database.
     * If no records exist, the counter is initialized to 0.
     */
    private static void loadMealID() {
        String getMealID = "SELECT MAX(meal_id) AS max_meal_id " +
                "FROM meals";

        try (PreparedStatement prep = CONNECTION.prepareStatement(getMealID);
             ResultSet res = prep.executeQuery()) {

            if (res.next()) {
                MEAL_ID.set(res.getInt("max_meal_id"));
            } else {
                MEAL_ID.set(0);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generates and returns a new unique meal ID.
     *
     * @return The next sequential meal ID.
     */
    public static int generateID() {
        return MEAL_ID.incrementAndGet();
    }
}
