package org.example;

import org.example.controller.Controller;
import org.example.util.FilesUtil;
import java.util.List;

public class Main {

    private static final String DIRECTORY = "data";
    private static final String CONTACT_FILE = "name_directory.txt";
    private static final String SEARCH_FILE = "find_people.txt";

    private static final List<String> CONTACT_LIST = initList(CONTACT_FILE);
    private static final List<String> SEARCH_LIST = initList(SEARCH_FILE);

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.run(CONTACT_LIST, SEARCH_LIST);
    }

    private static List<String> initList(String fileName) {
        String fullPath = FilesUtil.createFullPath(DIRECTORY, fileName);
        return FilesUtil.readFile(fullPath);
    }
}