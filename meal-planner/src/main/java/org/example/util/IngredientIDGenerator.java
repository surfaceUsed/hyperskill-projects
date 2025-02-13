package org.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class for generating unique ingredient IDs.
 * If an ingredient is already in the database, it will be assigned the same ID as previous entries.
 * Otherwise, it will be assigned a new ID.
 */
public final class IngredientIDGenerator {

    // Maps ingredient names to their assigned IDs.
    private static final Map<String, Integer> INGREDIENT_DATA_MAPPER = new HashMap<>();

    private static final Connection CONNECTION = ConnectionManager.getConnection();

    // Atomic integer to track and generate new ingredient IDs.
    private static final AtomicInteger INGREDIENT_ID = new AtomicInteger();

    private IngredientIDGenerator() {}

    // Static block to initialize ingredient ID mappings and load the highest existing ID.
    static {
        mapIngredientsById();
        loadCurrentMaxID();
    }

    /**
     * Populates the ingredient data map by retrieving existing ingredient names and their IDs from the database.
     * This ensures that duplicate ingredient names are assigned the same ID.
     */
    private static void mapIngredientsById() {
        String mapIngredientsByID = "SELECT ingredient, ingredient_id " +
                "FROM ingredients";

        try (PreparedStatement prep = CONNECTION.prepareStatement(mapIngredientsByID);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {
                String ingredient = res.getString("ingredient");
                int ingredientID = res.getInt("ingredient_id");
                INGREDIENT_DATA_MAPPER.putIfAbsent(ingredient, ingredientID);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to mapIngredients: " + e.getMessage());
        }
    }

    /**
     * Loads the highest existing ingredient ID from the database.
     * If there are no existing IDs, the counter is initialized to 0.
     */
    private static void loadCurrentMaxID() {
        final String query = "SELECT MAX(ingredient_id) AS max_ingredient_id FROM ingredients";

        try (PreparedStatement prep = CONNECTION.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {
            if (res.next()) {
                INGREDIENT_ID.set(res.getInt("max_ingredient_id"));
            } else {
                INGREDIENT_ID.set(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load current id: " + e.getMessage());
        }
    }

    /**
     * Generates a unique ID for a given ingredient.
     *
     * If the ingredient already exists in the map, its previously assigned ID is returned.
     * Otherwise, a new ID is generated and stored.
     *
     * @param ingredient The ingredient name.
     * @return The assigned ingredient ID.
     */
    public static int generateID(String ingredient) {
        if (INGREDIENT_DATA_MAPPER.get(ingredient) == null) {
            INGREDIENT_DATA_MAPPER.put(ingredient, INGREDIENT_ID.incrementAndGet());
        }
        return INGREDIENT_DATA_MAPPER.get(ingredient);
    }
}