package org.example.datasource;

import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import java.util.List;

public interface Datasource {

    void insertMeal(Meal meal);

    void createPlan(List<MealDayPlan> mealPlan);

    List<Meal> listMealPlan();

    List<Meal> listAll();

    List<Meal> listByCategory(String category);

    void clearTable(String tableName);
}
