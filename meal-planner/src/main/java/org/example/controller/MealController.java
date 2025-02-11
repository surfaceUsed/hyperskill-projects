package org.example.controller;

import org.example.dao.MealsDatabaseDAOImpl;
import org.example.datasource.Datasource;
import org.example.datasource.MealsDatabase;
import org.example.entity.Meal;
import org.example.entity.MealDayPlan;
import org.example.enums.ApplicationState;
import org.example.util.ConnectionManager;
import org.example.util.IOUtil;
import org.example.model.MealHandler;
import java.util.List;

public class MealController {

    private final static Datasource MEALS_DB = new MealsDatabase(MealsDatabaseDAOImpl.getInstance());
    private boolean isFinished;
    private ApplicationState state;

    public MealController() {
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
        this.state = ApplicationState.getState(IOUtil.writeInput());
    }

    private void addMeal() {

        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        String category = MealHandler.getMealCategory();

        System.out.println("Input the meal's name:");
        String mealName = MealHandler.getMealType();

        System.out.println("Input the ingredients:");
        String[] ingredients = MealHandler.getIngredients();

        MEALS_DB.insertMeal(new Meal(category, mealName, ingredients));
        System.out.println("The meal has been added!");

        this.state = ApplicationState.STATE_START;
    }

    private void show() {

        System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
        String mealCategory = MealHandler.getMealCategory();
        MealHandler.printMealsByCategory(MEALS_DB.listByCategory(mealCategory));

        this.state = ApplicationState.STATE_START;
    }

    private void plan() {

        List<MealDayPlan> mealPlan = MealHandler.addMealsTooPlan(MEALS_DB.listAll());
        MEALS_DB.createPlan(mealPlan);
        MealHandler.printMealPlan(mealPlan);

        this.state = ApplicationState.STATE_START;
    }

    private void save() {

        String ingredientList = MealHandler.getIngredientList(MEALS_DB.listMealPlan());

        if (!ingredientList.isEmpty()) {

            System.out.println("Input path to file you want to save the list to:");
            String filePath = IOUtil.writeInput();
            MealHandler.writeToFile(filePath, ingredientList);

        } else {

            System.out.println("Unable to save. Plan your meals first.");
        }

        this.state = ApplicationState.STATE_START;
    }

    private void exit() {

        IOUtil.closeInput();
        this.isFinished = true;
        ConnectionManager.closeConnection();
        System.out.println("Bye!");
    }
}
