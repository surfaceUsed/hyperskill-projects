import controller.CMLController;

public class Main {

    public static void main(String[] args) {

        String[] testInput = new String[]{"-sortingType", "byCount", "-dataType", "word", "-inputFile", "data/test.txt", "-outputFile","data/outputTest.txt"};

        new CMLController(testInput).runParser();
    }
}