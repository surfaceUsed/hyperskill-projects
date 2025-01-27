package org.example;

import org.example.controller.ReadabilityController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // args[0] -> insert path to file that contains the text to score.

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            ReadabilityController.analyzeText(br.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}