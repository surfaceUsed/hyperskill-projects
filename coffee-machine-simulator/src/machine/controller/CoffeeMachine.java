package machine.controller;

import machine.drinks.Coffee;
import machine.drinks.CoffeeFactory;
import machine.view.MachineView;
import java.util.Scanner;

public class CoffeeMachine {

    private static final String BUY_COFFEE = "buy";
    private static final String FILL_MACHINE = "fill";
    private static final String EMPTY_MONEY = "take";
    private static final String REMAINING = "remaining";
    private static final String EXIT = "exit";

    private final Scanner scanner = new Scanner(System.in);
    private final MachineView view;

    private int amountOfWater;
    private int amountOfMilk;
    private int amountOfBeans;
    private int amountOfCups;
    private int amountOfCash;

    public CoffeeMachine() {
        this.amountOfWater = 400;
        this.amountOfMilk = 540;
        this.amountOfBeans = 120;
        this.amountOfCups = 9;
        this.amountOfCash = 550;
        this.view = new MachineView(this);
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public int getAmountOfMilk() {
        return amountOfMilk;
    }

    public int getAmountOfBeans() {
        return amountOfBeans;
    }

    public int getAmountOfCups() {
        return amountOfCups;
    }

    public int getAmountOfCash() {
        return amountOfCash;
    }

    public void startMachine() {

        entryMessage();
        String input = scanner.nextLine();

        while (!EXIT.equals(input)) {

            switch (input) {

                case BUY_COFFEE:
                    buyCoffee();
                    break;

                case FILL_MACHINE:
                    addIngredients();
                    break;

                case EMPTY_MONEY:
                    emptyRegister();
                    break;

                case REMAINING:
                    printMenu();
                    break;

                default:
                    break;
            }

            entryMessage();
            input = scanner.nextLine();

        }

        this.scanner.close();
    }

    private void entryMessage() {
        this.view.choice();
    }

    private void buyCoffee() {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - " +
                "cappuccino, back - to main menu: ");

        Coffee coffee = CoffeeFactory.makeCoffee(scanner.nextLine());

        if (coffee != null) {
            updateMachine(coffee);
        }
    }

    private void updateMachine(Coffee coffee) {

        if (checkResources(coffee)) {

            this.amountOfWater -= coffee.getWaterPrServing();
            this.amountOfMilk -= coffee.getMilkPrServing();
            this.amountOfBeans -= coffee.getBeansPrServing();
            this.amountOfCups -= 1;
            this.amountOfCash += coffee.getPrice();

            System.out.println("I have enough resources, making you a coffee!");

        } else {

            System.out.println("Sorry, not enough resources");
        }
    }

    private boolean checkResources(Coffee coffee) {

        int needWater = coffee.getWaterPrServing();
        int needMilk = coffee.getMilkPrServing();
        int needBeans = coffee.getBeansPrServing();

        return ((this.amountOfWater >= needWater) && (this.amountOfMilk >= needMilk) &&
                (this.amountOfBeans >= needBeans) && (this.amountOfCups != 0));
    }

    private void addIngredients() {

        System.out.println("Write how many ml of water you want to add: ");
        this.amountOfWater += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add: ");
        this.amountOfMilk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add: ");
        this.amountOfBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add: ");
        this.amountOfCups += scanner.nextInt();
    }

    private void emptyRegister() {
        System.out.println("I gave you " + this.amountOfCash);
        this.amountOfCash = 0;
    }

    private void printMenu() {
        this.view.menu();
    }
}
