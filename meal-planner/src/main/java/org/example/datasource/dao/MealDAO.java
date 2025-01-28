package org.example.datasource.dao;

import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import java.util.List;

public interface MealDAO {

    void insertMeal(Meal meal);

    void createPlan(List<MealDayPlan> mealPlan);

    List<Meal> listMealPlan();

    List<Meal> listAllMeals();

    List<Meal> listMealByCategory(String category);
}