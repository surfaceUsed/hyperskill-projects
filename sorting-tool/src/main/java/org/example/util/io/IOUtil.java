package org.example.util.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class IOUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private IOUtil() {}

    public static List<String> readFromSystemInput() {
        List<String> inputList = new ArrayList<>();
        while (SCANNER.hasNextLine()) {
            String input = SCANNER.nextLine();
            if (input.equals("q")) {
                break;
            }
            inputList.add(input);
        }
        SCANNER.close();
        return inputList;
    }

    public static List<String> readFromFile(String fileName) throws IOException {
        return ReadFile.readFromFile(fileName);
    }

    public static void writeToFile(String fileName, String textToFile) throws IOException {
        WriteToFile.writeToFile(fileName, textToFile);
    }
}
