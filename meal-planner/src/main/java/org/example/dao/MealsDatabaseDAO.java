package org.example.dao;

import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import java.util.List;

public interface MealsDatabaseDAO {

    void insertMeal(Meal meal);

    void createPlan(List<MealDayPlan> mealPlan);

    List<Meal> listMealPlan();

    List<Meal> listAllMeals();

    List<Meal> listMealByCategory(String category);
}