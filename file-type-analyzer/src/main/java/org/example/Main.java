package org.example;

import org.example.system.FileAnalyzer;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // directory: "data"
        // File: "data/filename.type"
        FileAnalyzer analyzer = new FileAnalyzer(new File(args[0]), new File(args[1]));

        analyzer.init();
    }
}