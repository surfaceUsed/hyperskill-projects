package config;

import error.ApplicationInitiationException;
import util.FilesUtil;
import java.util.List;

public final class ConfigurationManager {

    private static ConfigurationManager manager;
    private static List<String> phoneBook;
    private static List<String> searchNames;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (manager == null) {
            manager = new ConfigurationManager();
        }
        return manager;
    }

    public static void loadInputFiles(String pathPhoneBook, String pathSearchNames) {
        loadPhoneBook(pathPhoneBook);
        loadSearchNames(pathSearchNames);
    }

    public List<String> getPhoneBookList() {
        if (phoneBook == null) {
            throw new ApplicationInitiationException("Phone book have not been initiated properly.");
        }
        return phoneBook;
    }

    public List<String> getSearchNames() {
        if (searchNames == null) {
            throw new ApplicationInitiationException("Search names have not been initiated properly.");
        }
        return searchNames;
    }

    private static void loadPhoneBook(String path) {
        phoneBook = FilesUtil.readFile(path);
    }

    private static void loadSearchNames(String path) {
        searchNames = FilesUtil.readFile(path);
    }
}
