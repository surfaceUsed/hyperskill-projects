package org.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Utility class for generating individual ingredient ID. If an ingredient is already in the database, the
 * ingredient will be assigned the same ID as the other previous entries; if there are no previous entries,
 * it will get its own ingredient ID.
 */

public final class IngredientIDGenerator {

    private static final Map<String, Integer> INGREDIENT_ID_MAPPER = new HashMap<>();
    private static final Connection CONNECTION = ConnectionManager.getConnection();

    private static int ingredientID;

    private IngredientIDGenerator() {}

    static {
        mapIngredientsById();
        loadIngredientID();
    }

    private static void mapIngredientsById() {
        String mapIngredientsByID = "SELECT ingredient, ingredient_id " +
                "FROM ingredients";

        try (PreparedStatement prep = CONNECTION.prepareStatement(mapIngredientsByID);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {
                String ingredient = res.getString("ingredient");
                int ingredientID = res.getInt("ingredient_id");
                INGREDIENT_ID_MAPPER.putIfAbsent(ingredient, ingredientID);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadIngredientID() {
        final String query = "SELECT MAX(ingredient_id) AS max_ingredient_id FROM ingredients";

        try (PreparedStatement prep = CONNECTION.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            if (res.next()) {
                ingredientID =  res.getInt("max_ingredient_id");
            } else {
                ingredientID = 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int generateID(String ingredient) {

        if (INGREDIENT_ID_MAPPER.putIfAbsent(ingredient, ingredientID + 1) == null) {

            ingredientID++;
        }
        return INGREDIENT_ID_MAPPER.get(ingredient);
    }
}
