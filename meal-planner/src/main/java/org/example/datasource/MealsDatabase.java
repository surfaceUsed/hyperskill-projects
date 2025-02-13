package org.example.datasource;

import org.example.dao.MealsDatabaseDAO;
import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import org.example.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * Class that initialises the database and creates three separate tables (meals, ingredients, plan)
 * if they don't already exist.
 *
 * To perform any operations on the database, the class has its own instance of a data access object "mealDao".
 */
public class MealsDatabase implements Datasource {

    private static final Connection CONNECTION = ConnectionManager.getConnection();

    private final static String CREATE_NEW_TABLE = "CREATE TABLE IF NOT EXISTS ";

    private final static String CLEAR_TABLE = "TRUNCATE TABLE %s";

    private final static String CREATE_TABLE_MEALS = CREATE_NEW_TABLE + "meals (category VARCHAR(15)," +
            " meal VARCHAR(25), meal_id INTEGER PRIMARY KEY)";

    private final static String CREATE_TABLE_INGREDIENTS = CREATE_NEW_TABLE + "ingredients (ingredient VARCHAR(15), " +
            "ingredient_id INTEGER, meal_id INTEGER, FOREIGN KEY (meal_id) REFERENCES meals(meal_id))";

    private final static String CREATE_TABLE_PLAN = CREATE_NEW_TABLE + "plan (day VARCHAR(15), meal_name VARCHAR(25), " +
            "meal_category VARCHAR(15), meal_id INTEGER, FOREIGN KEY (meal_id) REFERENCES meals(meal_id))";

    private final MealsDatabaseDAO mealDAO;

    static {
        loadDB();
    }

    public MealsDatabase(MealsDatabaseDAO mealDAO) {
        this.mealDAO = mealDAO;
    }

    private static void loadDB() {
        runQuery(CREATE_TABLE_MEALS);
        runQuery(CREATE_TABLE_INGREDIENTS);
        runQuery(CREATE_TABLE_PLAN);
    }

    private static void runQuery(String query) {

        try (PreparedStatement prep = CONNECTION.prepareStatement(query)) {

            prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertMeal(Meal meal) {
        this.mealDAO.insertMeal(meal);
    }

    @Override
    public void createPlan(List<MealDayPlan> mealPlan) {
        runQuery(String.format(CLEAR_TABLE, "plan"));
        this.mealDAO.createPlan(mealPlan);
    }

    @Override
    public List<Meal> listMealPlan() {
        return this.mealDAO.listMealPlan();
    }

    @Override
    public List<Meal> listAll() {
        return this.mealDAO.listAllMeals();
    }

    @Override
    public List<Meal> listByCategory(String category) {
        return this.mealDAO.listMealByCategory(category);
    }
}
