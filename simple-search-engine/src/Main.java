import config.ConfigurationManager;
import controller.Controller;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        ConfigurationManager.loadFile(new File("data/data.txt"));

        new Controller().run();

    }
}