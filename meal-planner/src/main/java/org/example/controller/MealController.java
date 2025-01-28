package org.example.controller;

import org.example.datasource.Datasource;
import org.example.datasource.MealsDB;
import org.example.datasource.dao.MealDAOImpl;
import org.example.datasource.dataUtil.ConnectionManager;
import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import org.example.enums.ApplicationState;
import org.example.util.MealHandler;
import java.util.List;
import java.util.Scanner;

public class MealController {

    private final static Datasource MEALS_DB = new MealsDB(MealDAOImpl.getInstance());

    private final Scanner scanner;

    private boolean isFinished;
    private ApplicationState state;

    public MealController() {
        this.scanner = new Scanner(System.in);
        this.isFinished = false;
        this.state = ApplicationState.STATE_START;
    }

    public void run() {

        while (!this.isFinished) {

            switch (this.state) {

                case STATE_START:
                    start();
                    break;

                case STATE_ADD:
                    addMeal();
                    break;

                case STATE_SHOW:
                    show();
                    break;

                case STATE_PLAN:
                    plan();
                    break;

                case STATE_SAVE:
                    save();
                    break;

                case STATE_EXIT:
                    exit();
                    break;

                case STATE_INVALID:
                    this.state = ApplicationState.STATE_START;
                    break;
            }
        }

    }

    private void start() {

        System.out.println("What would you like to do (add, show, plan, save, exit)?");
        this.state = ApplicationState.getState(this.scanner.nextLine());
    }

    private void addMeal() {

        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        String category = MealHandler.getMealCategory(this.scanner);

        System.out.println("Input the meal's name:");
        String mealName = MealHandler.getMealType(this.scanner);

        System.out.println("Input the ingredients:");
        String[] ingredients = MealHandler.getIngredients(this.scanner);

        MEALS_DB.insertMeal(new Meal(category, mealName, ingredients));
        System.out.println("The meal has been added!");

        this.state = ApplicationState.STATE_START;
    }

    private void show() {

        System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
        String mealCategory = MealHandler.getMealCategory(this.scanner);
        MealHandler.printMealsByCategory(MEALS_DB.listByCategory(mealCategory));

        this.state = ApplicationState.STATE_START;
    }

    private void plan() {

        List<MealDayPlan> mealPlan = MealHandler.addMealsTooPlan(MEALS_DB.listAll(), this.scanner);
        MEALS_DB.createPlan(mealPlan);
        MealHandler.printMealPlan(mealPlan);

        this.state = ApplicationState.STATE_START;
    }

    private void save() {

        String ingredientList = MealHandler.getIngredientList(MEALS_DB.listMealPlan());

        if (!ingredientList.isEmpty()) {

            System.out.println("Input a filename:");
            String fileName = this.scanner.nextLine();
            MealHandler.writeToFile(fileName, ingredientList);

        } else {

            System.out.println("Unable to save. Plan your meals first.");
        }

        this.state = ApplicationState.STATE_START;
    }

    private void exit() {

        this.scanner.close();
        this.isFinished = true;
        ConnectionManager.closeConnection();
        System.out.println("Bye!");
    }
}
