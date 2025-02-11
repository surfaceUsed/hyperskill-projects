package org.example.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IOUtil {

    private static final String SHOPPING_LIST_DIRECTORY = "shoppingList"; // Writes to default directory "shoppingList/" in root folder.
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String writeInput() {
        return SCANNER.nextLine();
    }

    public static void closeInput() {
        SCANNER.close();
    }

    public static void writeToFile(String filePath, String input) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {

            writer.write(input);
            System.out.println("Saved!");

        } catch (IOException e) {
            System.out.println("Error writing to file " + "\"" + file.getName() + "\": " + e.getMessage());
        }
    }
}
