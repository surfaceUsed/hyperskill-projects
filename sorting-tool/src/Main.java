import controller.CMLController;

public class Main {

    public static void main(String[] args) {

        String[] testInput = new String[]{"-sortingType", "byCount", "-dataType", "word", "-inputFile", "hello, how are you?"};

        new CMLController(testInput).runParser();
    }
}