package org.example.dao;

import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import org.example.util.ConnectionManager;
import org.example.util.IngredientIDGenerator;
import org.example.util.MealIDGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Data access object for performing queries to the database to insert, get, update and list
 * table entries.
 */

public class MealsDatabaseDAOImpl implements MealsDatabaseDAO {

    private static final String INSERT_INTO_TABLE = "INSERT INTO ";
    private final static Connection CONNECTION = ConnectionManager.getConnection();
    private final static MealsDatabaseDAO INSTANCE = new MealsDatabaseDAOImpl();

    private MealsDatabaseDAOImpl() {}

    public static MealsDatabaseDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertMeal(Meal meal) {

        meal.setMealID(MealIDGenerator.generateID());
        insertIntoMealTable(meal.getMealCategory(), meal.getMealName(), meal.getMealID());

        for (String ingredient : meal.getIngredients()) {
            insertIntoIngredientTable(ingredient, meal.getMealID());
        }
    }

    private void insertIntoMealTable(String mealCategory, String mealName, int mealID) {

        final String insertMeal = INSERT_INTO_TABLE + "meals (category, meal, meal_id) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement prep = CONNECTION.prepareStatement(insertMeal)) {

            prep.setString(1, mealCategory);
            prep.setString(2, mealName);
            prep.setInt(3, mealID);

            prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertIntoIngredientTable(String ingredient, int mealID) {

        final String insertIngredient = INSERT_INTO_TABLE + "ingredients (ingredient, ingredient_id, meal_id)" +
                "VALUES (?, ?, ?)";

        try(PreparedStatement prep = CONNECTION.prepareStatement(insertIngredient)) {

            prep.setString(1, ingredient);
            prep.setInt(2, IngredientIDGenerator.generateID(ingredient));
            prep.setInt(3, mealID);

            prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void createPlan(List<MealDayPlan> mealPlan) {

        for (MealDayPlan mealDayPlan : mealPlan) {
            addMealPlanEntry(mealDayPlan);
        }
    }

    private void addMealPlanEntry(MealDayPlan mealPlan) {
        final String addMealPlan = INSERT_INTO_TABLE + "plan (day, meal_name, meal_category, meal_id) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement prep = CONNECTION.prepareStatement(addMealPlan)) {

            Meal meal = mealPlan.getMeal();
            prep.setString(1, mealPlan.getDay());
            prep.setString(2, meal.getMealName());
            prep.setString(3, meal.getMealCategory());
            prep.setInt(4, meal.getMealID());
            prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println("addMealPlanEntry()");
            System.out.println(e.getMessage());
        }
    }

    public List<Meal> listMealPlan() {
        final String listWeeklyMealPlan = "SELECT meal_name, meal_category, meal_id " +
                "FROM plan";

        List<Meal> mealList = new ArrayList<>();

        try (PreparedStatement prep = CONNECTION.prepareStatement(listWeeklyMealPlan);
              ResultSet res = prep.executeQuery()) {

            while (res.next()) {

                int mealID = res.getInt("meal_id");

                mealList.add(
                        new Meal(
                                res.getString("meal_category"),
                                res.getString("meal_name"),
                                listIngredients(mealID),
                                mealID));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mealList;
    }

    @Override
    public List<Meal> listAllMeals() {

        final String listMealEntries = "SELECT * FROM meals " +
                "ORDER BY meal ASC";

        List<Meal> meals = new ArrayList<>();

        try (PreparedStatement prep = CONNECTION.prepareStatement(listMealEntries);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {

                int mealID = res.getInt("meal_id");

                meals.add(
                        new Meal(
                                res.getString("category"),
                                res.getString("meal"),
                                listIngredients(mealID),
                                mealID));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return meals;
    }

    private String[] listIngredients(int mealID) {

        final String listIngredients = "SELECT ingredient, meal_id " +
                "FROM ingredients " +
                "WHERE meal_id = " + mealID;

        String[] ingredientsList = null;

        try (PreparedStatement prep = CONNECTION.prepareStatement(listIngredients);
             ResultSet res = prep.executeQuery()) {

            StringBuilder sb = new StringBuilder();

            while (res.next()) {
                sb.append(res.getString("ingredient")).append(",");
            }

            ingredientsList = sb.toString().split(",");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ingredientsList;
    }

    @Override
    public List<Meal> listMealByCategory(String category) {

        final String listByCategory = "SELECT category, meal, meal_id " +
                "FROM meals " +
                "WHERE category = ?";

        List<Meal> meals = new ArrayList<>();

        try (PreparedStatement prep = CONNECTION.prepareStatement(listByCategory)) {

            prep.setString(1, category);

            try (ResultSet res = prep.executeQuery()) {

                while (res.next()) {
                    String mealCategory = res.getString("category");
                    String mealName = res.getString("meal");
                    int mealID = res.getInt("meal_id");
                    String[] ingredientList = listIngredients(mealID);
                    meals.add(
                            new Meal(
                                    mealCategory,
                                    mealName,
                                    ingredientList,
                                    mealID));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return meals;
    }
}
