package org.example.game.algorithm;

import org.example.game.GameModel;

import java.util.Random;

public class MediumAlgorithm implements GameAlgorithm {

    private final char gamePiece;
    private final GameModel model;
    private final char[][] gameTable;

    public MediumAlgorithm(GameModel model, char gamePiece) {
        this.model = model;
        this.gamePiece = gamePiece;
        this.gameTable = this.model.getGameTable();
    }

    @Override
    public char getGamePiece() {
        return this.gamePiece;
    }

    @Override
    public void gameLogic() {
        makeMediumPlay();
    }

    private void makeMediumPlay() {

        System.out.println("Making play level \"medium\"");

        int[] winningPlay = tacticalPlay(this.gamePiece);

        if (winningPlay != null) {
            this.model.updateGameTable(winningPlay[0] + 1, winningPlay[1] + 1, this.gamePiece);

        } else {

            char opponentGamePiece = this.model.getOpponentGamePiece(this.gamePiece);
            int[] blockingPlay = tacticalPlay(opponentGamePiece);

            if (blockingPlay != null) {
                this.model.updateGameTable(blockingPlay[0] + 1, blockingPlay[1] + 1, this.gamePiece);
            } else {
                makeRandomPlay();
            }
        }
    }

    private int[] tacticalPlay(char gamePiece) {

        int[] coordinates;

        for (int i = 0; i < this.gameTable.length; i++) {

            int indexColumn = 0, indexRow = 0;

            coordinates = checkTacticalPlay(new int[]{i, indexColumn}, new int[] {i, ++indexColumn},
                    new int[] {i, ++indexColumn}, gamePiece);
            if (coordinates != null) {
                return coordinates;
            }

            coordinates = checkTacticalPlay(new int[]{indexRow, i}, new int[]{++indexRow, i},
                    new int[]{++indexRow, i}, gamePiece);
            if (coordinates != null) {
                return coordinates;
            }
        }

        return checkDiagonal(gamePiece);
    }

    private int[] checkDiagonal(char gamePiece) {

        int[] coordinates;
        int indexRow = 0, indexColumn = 0;

        coordinates = checkTacticalPlay(new int[]{indexRow, indexColumn}, new int[]{indexRow + 1, indexColumn + 1},
                new int[]{indexRow + 2, indexColumn + 2}, gamePiece);
        if (coordinates != null) {
            return coordinates;
        }

        coordinates = checkTacticalPlay(new int[]{indexRow, indexColumn + 2}, new int[]{indexRow + 1, indexColumn + 1},
                new int[]{indexRow + 2, indexColumn}, gamePiece);
        return coordinates;
    }

    private int[] checkTacticalPlay(int[] indexOne, int[] indexTwo, int[] indexThree,
                                    char gamePiece) {
        int countGamePiece = 0;
        int x = 0;
        int y = 1;
        int[] coordinates = new int[2];

        if (this.gameTable[indexOne[x]][indexOne[y]] != ' ' &&
                this.gameTable[indexOne[x]][indexOne[y]] != gamePiece) {
            countGamePiece--;
            //return null;

        } else if (this.gameTable[indexOne[x]][indexOne[y]] == ' ') {
            coordinates = new int[]{indexOne[x], indexOne[y]};

        } else {
            countGamePiece++;
        }

        if (this.gameTable[indexTwo[x]][indexTwo[y]] != ' ' &&
                this.gameTable[indexTwo[x]][indexTwo[y]] != gamePiece) {
            countGamePiece--;
            //return null;

        } else if (this.gameTable[indexTwo[x]][indexTwo[y]] == ' ') {
            coordinates = new int[]{indexTwo[x], indexTwo[y]};

        } else {
            countGamePiece++;
        }

        if (this.gameTable[indexThree[x]][indexThree[y]] != ' ' &&
                gameTable[indexThree[x]][indexThree[y]] != gamePiece) {
            countGamePiece--;
            // return null;

        } else if (this.gameTable[indexThree[x]][indexThree[y]] == ' ' ) {
            coordinates = new int[]{indexThree[x], indexThree[y]};

        } else {
            countGamePiece++;
        }

        return (countGamePiece == 2) ? coordinates : null;

    }

    private void makeRandomPlay() {

        Random random = new Random();
        int x;
        int y;

        do {

            x = random.nextInt(3) + 1;
            y = random.nextInt(3) + 1;

        } while (this.gameTable[x - 1][y - 1] == GameModel.PLAYER_X || this.gameTable[x - 1][y - 1] == GameModel.PLAYER_O);

        this.model.updateGameTable(x, y, this.gamePiece);
    }
}