import controller.CMLController;

public class Main {

    public static void main(String[] args) {

        // Test input= String[] testInput = new String[]{"-sortingType", "byCount", "-dataType", "word", "-inputFile", "data/test.txt", "-outputFile", "outputTest.txt"};

        new CMLController(args).runParser();
    }
}