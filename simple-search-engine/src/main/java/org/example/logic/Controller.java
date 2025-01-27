package org.example.logic;

import java.util.Scanner;

public class Controller {

    enum Inputs {

        FIND_PERSON(1),
        LIST_ALL(2),
        EXIT(0),
        INVALID_VALUE(3);

        private final int value;

        Inputs(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        static Inputs getInput(int value) {
            for (Inputs inputs : Inputs.values()) {
                if (inputs.getValue() == value) {
                    return inputs;
                }
            }
            return INVALID_VALUE;
        }
    }

    private final FileParser fileHandler;
    private final Scanner scanner = new Scanner(System.in);

    private boolean isFinish = false;

    public Controller(String filePath) {
        this.fileHandler = new FileParser(filePath);
    }

    public void run() {

        while (!this.isFinish) {

            menu();
            Inputs input = Inputs.getInput(Integer.parseInt(this.scanner.nextLine()));

            if (input != Inputs.INVALID_VALUE) {

                switch (input) {

                    case FIND_PERSON:
                        findPerson();
                        break;

                    case LIST_ALL:
                        listAll();
                        break;

                    case EXIT:
                        exit();
                        break;
                }

            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }
    }

    private void findPerson() {

        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String queryStrategy = this.scanner.nextLine().toLowerCase();

        System.out.println("Enter a name or email to search all suitable people.");
        String query = this.scanner.nextLine().toLowerCase();

        this.fileHandler.queryList(queryStrategy, query);
    }

    private void listAll() {
        System.out.println("=== List of people ===");
        this.fileHandler.printList();
    }

    private void exit() {
        isFinish = true;
        scanner.close();
        System.out.println("Bye!");
    }

    private void menu() {
        System.out.println("""
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit
                """);
    }
}