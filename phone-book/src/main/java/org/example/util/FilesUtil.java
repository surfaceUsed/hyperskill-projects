package org.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {

    public static List<String> readFile(String filePath) {
        List<String> fileAsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                fileAsList.add(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileAsList;
    }

    // For use if needed.
    public static void writeToFile(String filePath, List<String> listToWrite) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (String contact : listToWrite) {
                writer.write(contact);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String createFullPath(String directoryPath, String fileName) {
        return directoryPath + File.separator + fileName;
    }
}
