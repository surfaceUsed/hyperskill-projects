package org.example;

import org.example.controller.CMLController;

/**
 *
 * Reads data/test.txt file, and prints the sorted version to file data/outputTest.txt
 */
public class Main {

    public static void main(String[] args) {

        String[] testInput = new String[]{"-sortingType", "byCount", "-dataType", "word", "-inputFile", "data\\test.txt", "-outputFile","data\\outputTest.txt"};

        new CMLController(testInput).runParser();
    }
}