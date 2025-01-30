package game;

import game.util.RandomStringGenerator;

import java.util.Scanner;

public class Game {

    private static final int BULL = 0;
    private static final int COW = 1;

    private final int secretCodeLength;
    private final int numberOfSymbols;
    private final String secretCode;
    private final Scanner scanner = new Scanner(System.in);
    private final int[] countTab = new int[2];

    private boolean isFinish = false;

    public Game(int secretCodeLength, int numberOfSymbols) {
        this.secretCodeLength = secretCodeLength;
        this.numberOfSymbols = numberOfSymbols;
        this.secretCode = RandomStringGenerator.createRandomString(numberOfSymbols).substring(0, secretCodeLength);
    }

    public void play() {

        System.out.println("The secret is prepared: " + printIntervals());
        System.out.println("Okay, let's start the game!");
        int turn = 0;

        while (!this.isFinish) {
            System.out.println("Turn " + (++turn) + ": ");
            String guess = this.scanner.nextLine();
            checkNumbers(guess);
        }
    }

    private String printIntervals() {

        if (this.numberOfSymbols < RandomStringGenerator.MAX_AMOUNT_OF_DIGITS) {

            return printStars() + " (0-" + (this.numberOfSymbols - 1)+ ")";

        } else if(this.numberOfSymbols > RandomStringGenerator.MAX_AMOUNT_OF_DIGITS) {

            return printStars() + " (0-9, a-" +
                    (char)('a' + (this.numberOfSymbols - RandomStringGenerator.MAX_AMOUNT_OF_DIGITS) - 1) + ")";
        }

        return printStars() + " (0-9)";
    }

    private String printStars() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.secretCodeLength; i++) {
            sb.append("*");
        }

        return sb.toString();
    }

    private void checkNumbers(String input) {

        if (this.secretCodeLength == input.length()) {

            for (int i = 0; i < this.secretCodeLength; i++) {

                if (this.secretCode.charAt(i) == input.charAt(i)) {
                    this.countTab[BULL]++;

                } else {

                    checkCow(i, input);
                }
            }

        } else {
            System.out.println("The input is not the same length as the secret code.");
        }

        checkWinner();
    }

    private void checkCow(int index, String input) {
        for (int i = 0; i < this.secretCodeLength; i++) {
            if (this.secretCode.charAt(index) == input.charAt(i)) {
                this.countTab[COW]++;
            }
        }
    }

    private void checkWinner() {

        System.out.println(result());

        if (this.countTab[BULL] == this.secretCodeLength) {
            this.isFinish = true;
            System.out.println("Congratulations! You guessed the secret code.");

        } else {

            this.countTab[BULL] = 0;
            this.countTab[COW] = 0;
        }
    }

    private String result() {

        final String bull = "bull", cow = "cow";

        int numberOfBulls = this.countTab[BULL], numberOfCows = this.countTab[COW];

        if (numberOfBulls == this.secretCodeLength) {
            return "Grade: " + numberOfBulls + " " + checkPlural(bull, numberOfBulls);

        } else if (numberOfBulls > 0 && numberOfCows > 0) {
            return "Grade: " + numberOfBulls + " " + checkPlural(bull, numberOfBulls) +
                    " and " + numberOfCows + " " + checkPlural(cow, numberOfCows);

        } else if (numberOfBulls > 0 && numberOfCows == 0) {
            return "Grade: " + numberOfBulls + " " + checkPlural(bull, numberOfBulls);

        } else if (numberOfBulls == 0 && numberOfCows > 0) {
            return "Grade: " + numberOfCows + " " + checkPlural(cow, numberOfCows);

        }

        return "Grade: " + numberOfBulls + " " + bull + " and " + numberOfCows + " " + cow;
    }

    private String checkPlural(String bullOrCow, int numb) {
        return (numb > 1) ? bullOrCow + "s" : bullOrCow;
    }
}
