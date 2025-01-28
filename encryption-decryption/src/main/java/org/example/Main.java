package org.example;

import org.example.controller.Controller;

public class Main {

    public static void main(String[] args) {

        String[] a = new String[]{"-mode", "enc", "-data", "Hello there!", "-key", "5", "-alg", "unicode"};

        new Controller(a).runCipher();
    }
}