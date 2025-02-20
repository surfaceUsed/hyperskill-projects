import controller.CMLController;

public class Main {

    public static void main(String[] args) {

        // Test input=
        String[] testInput = new String[]{"-sortingType", "byCount", "-dataType", "word", "-inputFile", "data/test.txt", "-outputFile", "outputTest.txt"};
        String[] testInpu2 = new String[]{"-sortingType", "byCount", "-dataType", "word", "-inputFile", "data/test.txt"};

        String[] testInput1 = new String[]{"-sortingType", "natural", "-dataType", "long", "-inputFile", "data/testNumbers.txt"};

        new CMLController(testInput1).runParser();
    }
}