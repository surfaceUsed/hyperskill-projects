package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            throw new RuntimeException("Failed to read file: " + e.getMessage());
        }
        return fileAsList;
    }
}
