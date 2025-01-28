package org.example.entity;

public class Meal {

    private final String mealCategory;
    private final String mealName;
    private final String[] ingredients;

    private int mealID;

    public Meal(String mealCategory, String mealName, String[] ingredients) {
        this.mealCategory = mealCategory;
        this.mealName = mealName;
        this.ingredients = ingredients;
    }

    public Meal(String mealCategory, String mealName, String[] ingredients, int mealID) {
        this.mealCategory = mealCategory;
        this.mealName = mealName;
        this.ingredients = ingredients;
        this.mealID = mealID;
    }

    public String getMealCategory() {
        return this.mealCategory;
    }

    public String getMealName() {
        return this.mealName;
    }

    public String[] getIngredients() {
        return this.ingredients;
    }

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String printIngredients() {
        StringBuilder sb = new StringBuilder();
        for (String item : this.ingredients) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Meal id: " + mealID +
                "\nCategory: " + this.mealCategory +
                "\nName: " + this.mealName +
                "\nIngredients: " +
                "\n" + printIngredients();
    }
}
