package config;

import java.io.File;
import java.io.IOException;

public final class ConfigurationManager {

    private final static File defaultPath = new File("database/default_database_storage.db");
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

    /**
     *
     * Loads a file-object. If the file-parameter is invalid, a default file will be created 'database/default_database_storage.db'
     * in the local 'database/'-directory
     */
    private boolean loadSource(File file) throws IOException {
        boolean fileExists = true;
        if (file != null && file.exists() && file.isFile()) {
            path = file;
        } else {
            path = defaultPath;
            fileExists = path.createNewFile();
        }
        return fileExists;
    }

    public void loadPhoneBookFromSource(File file) {
        try {
            if (loadSource(file)) {
                phonebook = PhoneBook.loadPhonebook(path);
            } else {
                throw new RuntimeException("File with name '" + path.getName() + "' already exists in directory '" +
                        path.getParent() + "'.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Critical error, failed to create default storage '" +
                    defaultPath.toString() + "'" + e.getMessage());
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
