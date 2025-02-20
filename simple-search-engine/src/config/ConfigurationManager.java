package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationManager {

    private static ConfigurationManager manager;
    private static File loadFile;
    private static List<String> fileLines; // Stores each line from the file.
    private static Map<String, List<Integer>> invertedIndex; // Inverted index; maps words to line numbers.

    private ConfigurationManager() {}

    // A file needs to be loaded before a valid instance can be called.
    public static ConfigurationManager getInstance() {
        if (loadFile == null) {
            throw new FileParserException("No file to read from.");
        }
        if (manager == null) {
            manager = new ConfigurationManager();
        }
        return manager;
    }

    public static void loadFile(File file) {
        if (file.exists() && file.isFile()) {
            loadFile = file;
        } else {
            throw new FileParserException("The input file is not valid, and could not be loaded by the application.");
        }
    }

    // Lazy init.
    public List<String> getFileLinesAsList() {
        if (fileLines == null) {
            fileLines = loadFileAsList();
        }
        return fileLines;
    }

    // Lazy init.
    public Map<String, List<Integer>> getInvertedIndex() {
        if (invertedIndex == null) {
            invertedIndex = loadInvertedIndex();
        }
        return invertedIndex;
    }

    private static List<String> loadFileAsList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFile))) {
            List<String> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            throw new FileParserException("Failed to read from file '" + loadFile.getName() + "': " + e.getMessage());
        }
    }

    private static Map<String, List<Integer>> loadInvertedIndex() {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < fileLines.size(); i++) {
            String[] splitLine = fileLines.get(i).toLowerCase().split("\\s+");
            int index = 0;
            while (index < splitLine.length) {
                map.putIfAbsent(splitLine[index], new ArrayList<>());
                if (map.containsKey(splitLine[index])) {
                    map.get(splitLine[index]).add(i);
                }
                index++;
            }
        }
        return map;
    }
}
