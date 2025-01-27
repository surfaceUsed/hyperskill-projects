package org.example;

import org.example.logic.Controller;

public class Main {
    public static void main(String[] args) {

        new Controller("src/main/java/org/example/data/data.txt").run();

    }
}