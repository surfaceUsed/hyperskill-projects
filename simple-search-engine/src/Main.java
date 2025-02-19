import config.ConfigurationManager;
import controller.Controller;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        // test file path: "data/data.txt".

        ConfigurationManager.loadFile(new File(args[0]));

        new Controller().run();
    }
}