package org.example.entity;

/**
 *
 * Class for creating a meal-object for a specific day.
 */

public class MealDayPlan {

    private final String day;
    private final Meal meal;

    public MealDayPlan(String day, Meal meal) {
        this.day = day;
        this.meal = meal;
    }

    public String getDay() {
        return this.day;
    }

    public Meal getMeal() {
        return this.meal;
    }
}
