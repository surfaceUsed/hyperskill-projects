import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String[] NAMES = {"John", "Jack"};

    public static void main(String[] args) {

        startGame();
    }

    private static void startGame() {

        int numberOfPencils = getNumberOfPencils();
        String playerOne = getPlayer();
        String playerTwo = NAMES[getBot(playerOne)];
        new Game(new Player(playerOne), new Player(playerTwo), numberOfPencils).start();
    }

    private static String getPlayer() {

        System.out.println("Who will be the first (John, Jack):");
        String name = SCANNER.nextLine();

        while (!isValidName(name)) {
            System.out.println("Choose between 'John' and 'Jack'");
            name = SCANNER.nextLine();
        }

        return name;
    }

    private static int getBot(String name) {

        for (int i = 0; i < NAMES.length; i++) {
            if (!NAMES[i].equals(name)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isValidName(String name) {
        return NAMES[0].equals(name) || NAMES[1].equals(name);
    }

    private static int getNumberOfPencils() {

        System.out.println("How many pencils would you like to use:");
        boolean isValidNumber = false;
        String input = SCANNER.nextLine();

        while (!isValidNumber) {

            if (isNumber(input)) {
                isValidNumber = checkInputNumber(Integer.parseInt(input));
            }

            if (!isValidNumber) {
                input = SCANNER.nextLine();
            }
        }

        return Integer.parseInt(input);
    }

    private static boolean checkInputNumber(int numb) {

        if (numb <= 0) {
            String output = (numb == 0) ? "The number of pencils should be positive" : "The number of pencils should be numeric";
            System.out.println(output);
            return false;
        }

        return true;
    }

    private static boolean isNumber(String input) {

        if (!input.isEmpty()) {

            try {
                Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                return false;
            }

            return true;
        }

        return false;
    }
}