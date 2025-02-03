package org.example;

import org.example.system.FileAnalyzer;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            FileAnalyzer analyzer = new FileAnalyzer(new File(args[0]), new File(args[1]));
            analyzer.init();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}