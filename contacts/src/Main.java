import config.ConfigurationManager;
import controller.Controller;
import java.io.File;

public class Main {
    
    public static void main(String[] args) {

        ConfigurationManager.getInstance().loadPhonebookFromSource(new File("database/phonebook.db"));
        new Controller().start();
    }
}