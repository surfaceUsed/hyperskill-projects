package util.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class IOUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private IOUtil() {}

    public static void closeScanner() {
        SCANNER.close();
    }

    /**
     *
     * Reads user input from the console (System.in) line by line.
     * Input is collected into a list of strings, and the loop stops when the user types "q".
     *
     * @return A list of strings representing the user input, excluding "q".
     */
    public static List<String> readFromSystemInput() {
        List<String> inputList = new ArrayList<>();
        while (SCANNER.hasNextLine()) {
            String input = SCANNER.nextLine();
            if (input.equals("q")) {
                break;
            }
            inputList.add(input);
        }
        return inputList;
    }

    public static List<String> readFromFile(String filePath) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        }
    }

    /**
     *
     * Writes to a file.
     * If file already exists, the new file data will override previous data.
     */
    public static void writeToFile(String filePath, String text) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write(text);
        }
    }
}
