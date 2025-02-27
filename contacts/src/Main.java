import config.ConfigurationManager;
import controller.Controller;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        // test directory: database/phonebook.db

        ConfigurationManager.getInstance().loadPhoneBookFromSource(new File(args[0]));
        new Controller().start();
    }
}