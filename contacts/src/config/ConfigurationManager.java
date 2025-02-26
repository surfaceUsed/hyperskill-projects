package config;

import java.io.File;

public final class ConfigurationManager {

    private static ConfigurationManager manager;
    private File path;
    private PhoneBook phonebook;

    private ConfigurationManager() {}

    public static ConfigurationManager getInstance() {
        if (manager == null) {
            manager = new ConfigurationManager();
        }
        return manager;
    }

    public void loadPhonebookFromSource(File file) {
        if (file.exists() && file.isFile()) {
            path = file;
            phonebook = PhoneBook.loadPhonebook(path);
        } else {
            throw new RuntimeException("File path '" + file + "' is not valid.");
        }
    }

    public PhoneBook getPhonebook() {
        if (phonebook == null) {
            throw new RuntimeException("Phonebook has not been loaded properly yet.");
        }
        return phonebook;
    }

    public File getPath() {
        if (path == null) {
            throw new RuntimeException("A file path to the phonebook location file has not been initialized.");
        }
        return path;
    }
}
