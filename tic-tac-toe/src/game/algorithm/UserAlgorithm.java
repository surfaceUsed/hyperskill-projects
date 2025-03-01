package game.algorithm;

import game.GameModel;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserAlgorithm implements GameAlgorithm {

    private final char gamePiece;
    private final GameModel model;
    private final char[][] gameTable;
    private final Scanner scanner;

    public UserAlgorithm(GameModel model, char gamePiece) {
        this.model = model;
        this.gamePiece = gamePiece;
        this.gameTable = this.model.getGameTable();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public char getGamePiece() {
        return this.gamePiece;
    }

    @Override
    public void gameLogic() {

        int x = -1;
        int y = -1;
        boolean isCorrectCoordinates = false;

        do {
            try {


                System.out.println("Enter the coordinates: ");

                x = this.scanner.nextInt();
                y = this.scanner.nextInt();
                this.scanner.nextLine();

                if ((x < 1 || x > 3) || (y < 1 || y > 3)) {

                    System.out.println("Coordinates should be from 1 to 3!");

                } else {

                    if (this.gameTable[x - 1][y - 1] == GameModel.PLAYER_X ||
                            this.gameTable[x - 1][y - 1] == GameModel.PLAYER_O) {

                        System.out.println("This cell is occupied! Choose another one!");

                    } else {

                        isCorrectCoordinates = true;

                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Input a number!");
                this.scanner.nextLine();
            }

        } while (!isCorrectCoordinates);


        this.model.updateGameTable(x, y, this.gamePiece);
    }
}
