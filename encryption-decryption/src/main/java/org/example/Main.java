package org.example;

import org.example.controller.Controller;

public class Main {

    public static void main(String[] args) {

        /*

         Set of input tests to run:

         Test 1, encrypts the text 'Hello there!, shifts 5 letters and uses the unicode algorithm.
         String[] a = new String[]{"-mode", "enc", "-data", "Hello there!", "-key", "5", "-alg", "unicode"};

         Test 2, encrypts a text from the file 'readFromFile.txt (in data directory), shifts 5 letters using
         the unicode algorithm, and writes the result to the file 'writeToFile.txt (in the 'data' directory).
         String[] b = new String[]{"-mode", "enc", "-in", "data/readFromFile.txt", "-key", "5", "-alg", "unicode", "-out", "data/writeToFile.txt"};
         */

        new Controller(args).runCipher();
    }
}