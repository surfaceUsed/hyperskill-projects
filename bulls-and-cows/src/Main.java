import game.Game;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_NUMBER_OF_SYMBOLS = 36;

    public static void main(String[] args) {

        startGame();

    }

    private static void startGame() {

        System.out.println("Input the length of the secret code:");
        String codeLength = scanner.nextLine();

        if (isNumber(codeLength)) {

            System.out.println("Input the number of possible symbols in the code:");
            String numberOfSymbols = scanner.nextLine();

            if (isNumber(numberOfSymbols)) {

                if (Integer.parseInt(codeLength) <= Integer.parseInt(numberOfSymbols)) {

                    if (isInsideInterval(Integer.parseInt(numberOfSymbols))) {

                        new Game(Integer.parseInt(codeLength), Integer.parseInt(numberOfSymbols)).play();
                        scanner.close();

                    } else {

                        System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    }

                } else {

                    System.out.println("Error: it's not possible to generate a code with a length of " + Integer.parseInt(codeLength) +
                            " with " + Integer.parseInt(numberOfSymbols) + " unique symbols.");
                }
            }
        }

        scanner.close();
    }

    private static boolean isInsideInterval(int numb) {
        return numb <= MAX_NUMBER_OF_SYMBOLS;
    }

    private static boolean isNumber(String numb) {

        try {
            Integer.parseInt(numb);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + numb + " is not a valid number.");
        }

        return false;
    }
}