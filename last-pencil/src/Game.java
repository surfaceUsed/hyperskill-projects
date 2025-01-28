import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final char PENCIL = '|';

    private final Scanner scanner = new Scanner(System.in);
    private final Player playerOne;
    private final Player playerTwo;

    private Player nextPlayer;
    private boolean isWinner = false;
    private int numberOfPencils;

    public Game(Player playerOne, Player playerTwo, int numberOfPencils) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerTwo.setIsBot(true);
        this.numberOfPencils = numberOfPencils;
    }

    public void start() {

        int playerTracker = 0;

        while (!this.isWinner) {

            System.out.println(printPencil(this.numberOfPencils));

            if (playerTracker % 2 == 0) {
                System.out.println(this.playerOne.getPlayerName() + "'s turn!");
                play(this.playerOne);
                this.nextPlayer = this.playerTwo;

            } else {

                System.out.println(this.playerTwo.getPlayerName() + "'s turn!");
                play(this.playerTwo);
                this.nextPlayer = this.playerOne;
            }

            playerTracker++;
        }

        System.out.println(this.nextPlayer.getPlayerName() + " won!");
    }


    public void play(Player player) {

        if (player.isPlayerBot()) {
            botPlay();
        } else {
            playerPlay();
        }
    }

    public void botPlay() {

        if (this.numberOfPencils % 4 == 0) {
            // take 3 pencils
            this.numberOfPencils -= 3;
            checkIfWinner();

        } else if(this.numberOfPencils % 4 == 3) {
            // take 2 pencils
            this.numberOfPencils -= 2;
            checkIfWinner();

        } else if (this.numberOfPencils % 4 == 2) {
            // take 1 pencil
            this.numberOfPencils -= 1;
            checkIfWinner();

        } else if (this.numberOfPencils == 1) {
            // take 1 pencil, loose
            this.numberOfPencils -= 1;
            checkIfWinner();

        } else {
            // loosing position, random.nextInt(3) + 1; Choose number between 1 and 3.
            this.numberOfPencils -= removeRandomNumberOfPencils();
        }
    }

    private void checkIfWinner() {
        if (numberOfPencils == 0) {
            isWinner = true;
        }
    }

    private int removeRandomNumberOfPencils() {
        return new Random().nextInt(3) + 1;
    }

    private void playerPlay() {

        boolean isFinished = false;

        while (!isFinished) {

            String input = this.scanner.nextLine();
            if (isNumber(input)) {
                isFinished = removePencil(Integer.parseInt(input));
            }
        }
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

    private boolean removePencil(int numb) {

        int dif = this.numberOfPencils - numb;

        if (numb >= 1 && numb <= 3) {

            if (dif > 0) {
                this.numberOfPencils = dif;
                return true;

            } else if (dif < 0) {
                System.out.println("Too many pencils were taken");
                return false;

            } else {
                this.isWinner = true;
                this.numberOfPencils = dif;
                return true;
            }
        }

        System.out.println("Possible values: '1', '2', '3'");
        return false;
    }

    public String printPencil(int numb) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numb; i++) {
            sb.append(PENCIL);
        }

        return sb.toString();
    }
}
