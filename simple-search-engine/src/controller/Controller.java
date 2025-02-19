package controller;

import enums.Inputs;
import logic.TextFileSearcher;
import java.util.Scanner;

public class Controller {

    private final TextFileSearcher searcher;
    private final Scanner scanner = new Scanner(System.in);

    private boolean isFinish = false;

    public Controller() {
        this.searcher = new TextFileSearcher();
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

    /**
     *
     * User chooses strategy (ALL, ANY, NONE), and enters a search query.
     */
    private void findPerson() {

        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String queryStrategy = this.scanner.nextLine().toLowerCase();
        System.out.println();

        System.out.println("Enter a name or email to search all suitable people.");
        String query = this.scanner.nextLine().toLowerCase();
        System.out.println();

        this.searcher.queryIndex(queryStrategy, query);
    }

    /**
     *
     * Prints all the entries from the text file.
     */
    private void listAll() {
        System.out.println("=== List of people ===");
        this.searcher.printList();
    }

    private void exit() {
        isFinish = true;
        scanner.close();
        System.out.println("\nBye!");
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
