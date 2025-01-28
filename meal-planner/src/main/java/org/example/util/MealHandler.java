package org.example.util;

import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import org.example.enums.DaysOfTheWeek;
import org.example.enums.MealCategory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealHandler {

    private static final String SHOPPING_LIST_DIRECTORY = "shoppingList";
    private MealHandler() {
    }

    public static String getMealCategory(Scanner scanner) {

        String input = scanner.nextLine();
        MealCategory category;

        do {

            while (!isFoodInputValid(input)) {
                System.out.println("Wrong format. Use letters only!");
                input = scanner.nextLine();
            }

            category = MealCategory.getMealCategory(input);
            if (category == MealCategory.INVALID_MEAL) {
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
                input = scanner.nextLine();
                category = MealCategory.getMealCategory(input);
            }

        } while (category == MealCategory.INVALID_MEAL || !isFoodInputValid(input));

        return input;
    }

    public static String getMealType(Scanner scanner) {

        String input = scanner.nextLine();

        while (!isFoodInputValid(input)) {
            System.out.println("Wrong format. Use letters only!");
            input = scanner.nextLine();
        }

        return input;
    }

    public static String[] getIngredients(Scanner scanner) {

        String ingredientsAsText = scanner.nextLine();
        String[] splitIngredients;
        boolean isValid;

        do {

            isValid = true;
            splitIngredients = ingredientsAsText.split(",");

            for (String ingredient : splitIngredients) {
                if (!isFoodInputValid(ingredient)) {
                    System.out.println("Wrong format. Use letters only!");
                    ingredientsAsText = scanner.nextLine();
                    isValid = false;
                    break;
                }
            }

        } while (!isValid);

        return splitIngredients;
    }

    private static boolean isFoodInputValid(String input) {
        final String regEx = "[a-z A-Z]*";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(input);
        return (matcher.matches() && !input.isEmpty() && !input.equals(" "));
    }

    public static void printMealsByCategory(List<Meal> mealList) {

        if (!mealList.isEmpty()) {

            System.out.println("Category: " + mealList.get(0).getMealCategory() + "\n");

            for (Meal meal : mealList) {
                System.out.println("Name: " + meal.getMealName());
                System.out.println("ingredients:\n" +
                        meal.printIngredients() + "\n");
            }

        } else {
            System.out.println("No meals found.");
        }
    }

    public static List<MealDayPlan> addMealsTooPlan(List<Meal> mealList, Scanner scanner) {

        List<MealDayPlan> mealPlanner = new ArrayList<>();

        for (DaysOfTheWeek day : DaysOfTheWeek.values()) {

            String dayName = day.getDay();
            System.out.println(dayName);

            for (MealCategory category : MealCategory.values()) {

                if (category != MealCategory.INVALID_MEAL) {
                    String mealCategory = category.getMealCategory();
                    List<Meal> orderedMealList = sortMealsByCategory(mealList, mealCategory);
                    printMeals(orderedMealList);
                    System.out.println("Choose the " + mealCategory + " for " + dayName + " from the list above:");
                    Meal input = getMeal(orderedMealList, scanner);
                    mealPlanner.add(new MealDayPlan(dayName, input));
                }

            }

            System.out.println("Yeah! We planned the meals for " + dayName + ".");
        }

        return mealPlanner;
    }

    private static List<Meal> sortMealsByCategory(List<Meal> meals, String category) {

        List<Meal> mealsByCategory = new ArrayList<>();

        for (Meal meal : meals) {
            if (meal.getMealCategory().equals(category)) {
                mealsByCategory.add(meal);
            }
        }

        return mealsByCategory;
    }

    private static void printMeals(List<Meal> mealList) {

        for (Meal meal : mealList) {
            System.out.println(meal.getMealName());
        }
    }

    private static Meal getMeal(List<Meal> mealList, Scanner scanner) {

        do {

            String input = scanner.nextLine();

            for (Meal meal : mealList) {
                String mealName = meal.getMealName();
                if (mealName.equalsIgnoreCase(input)) {
                    return meal;
                }
            }

            System.out.println("This meal doesn’t exist. Choose a meal from the list above.");

        } while (true);
    }

    public static void printMealPlan(List<MealDayPlan> mealList) {

        for (DaysOfTheWeek day : DaysOfTheWeek.values()) {

            String dayName = day.getDay();

            System.out.println(dayName);

            for (MealDayPlan mealPlan : mealList) {

                if (mealPlan.getDay().equals(dayName)) {

                    Meal meal = mealPlan.getMeal();
                    String mealCategory = meal.getMealCategory();
                    String mealName = meal.getMealName();
                    System.out.println(mealCategory + ": " + mealName);
                }
            }

            System.out.println();
        }
    }

    public static String getIngredientList(List<Meal> mealList) {

        Map<String, Integer> ingredientMapper = mapIngredients(mealList);

        StringBuilder sb = new StringBuilder();

        for (String ingredient : ingredientMapper.keySet()) {

            int value = ingredientMapper.get(ingredient);

            if (value > 1) {
                sb.append(ingredient).append(" ").append("x").append(value);
            } else {
                sb.append(ingredient);
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    private static Map<String, Integer> mapIngredients(List<Meal> mealList) {

        final int initialValue = 1;

        Map<String, Integer> ingredientMapper = new HashMap<>();

        for (Meal meal : mealList) {

            for (String ingredient : meal.getIngredients()) {

                if (ingredientMapper.containsKey(ingredient)) {
                    int value = ingredientMapper.get(ingredient);
                    ingredientMapper.put(ingredient, value + 1);

                } else {

                    ingredientMapper.put(ingredient, initialValue);
                }
            }
        }

        return ingredientMapper;
    }

    public static void writeToFile(String fileName, String input) {

        File file = new File(SHOPPING_LIST_DIRECTORY, fileName);
        try (FileWriter writer = new FileWriter(file)) {

            writer.write(input);
            System.out.println("Saved!");

        } catch (IOException e) {

            System.out.println("Error writing to file " + "\"" + fileName + "\": " + e.getMessage());
        }
    }
}