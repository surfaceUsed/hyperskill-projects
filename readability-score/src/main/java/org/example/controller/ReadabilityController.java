package org.example.controller;

import org.example.readabilityTests.TestFactory;
import org.example.util.Calculations;
import org.example.util.TestType;

import java.util.Scanner;

public class ReadabilityController {

    private ReadabilityController() {}

    public static void analyzeText(String text) {
        System.out.printf("""
                The text is:
                %s
                
                """, text);

        int amountOfWords = Calculations.getAmountOfWords(text);
        int amountOfSentences = Calculations.getAmountOfSentences(text);
        int amountOfCharacters = Calculations.getAmountOfCharacters(text);
        int amountOfSyllables = Calculations.getAmountOfSyllables(text);
        int amountOfPolysyllables = Calculations.getPolysyllables(text);

        System.out.println(displayResult(amountOfWords, amountOfSentences, amountOfCharacters, amountOfSyllables,
                amountOfPolysyllables));

        System.out.println(chooseText(text));
    }

    private static String displayResult(int amountOfWords, int amountOfSentences, int amountOfCharacters,
                                        int amountOfSyllables, int amountOfPolysyllables) {
        return String.format("""
                Words: %d
                Sentences: %d
                Characters: %d
                Syllables: %d
                Polysyllables: %d
                """, amountOfWords, amountOfSentences, amountOfCharacters, amountOfSyllables, amountOfPolysyllables);
    }

    private static String chooseText(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        String input = scanner.nextLine();
        System.out.println();
        String message = "Invalid input";

        switch (input) {
            case "ARI", "FK", "SMOG", "CL":
                message = TestFactory.getTest(input).getResult(text);
                break;
            case "all":
                message = printAllTests(text);
                break;
            default:
                break;
        }
        return message;
    }

    private static String printAllTests(String text) {
        StringBuilder sb = new StringBuilder();
        String[] keys = TestType.getSearchKeys();
        for (String tests : keys) {
            sb.append(TestFactory.getTest(tests).getResult(text));
            sb.append("\n");
        }
        return sb.append("\n").append(getAverageAge(text)).toString();
    }

    private static String getAverageAge(String text) {
        return String.format("This text should be understood on average by %.2f-year-olds.",
                Calculations.getAverageReadingLevel(text));
    }
}
