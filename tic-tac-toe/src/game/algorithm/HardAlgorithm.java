package game.algorithm;

import game.GameModel;

public class HardAlgorithm implements GameAlgorithm {

    private final GameModel model;
    private final char gamePiece;
    private final char[][] gameTable;

    public HardAlgorithm(GameModel model, char gamePiece) {
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
        makeHardPlay();
    }

    private void makeHardPlay() {

        final int x = 0;
        final int y = 1;

        System.out.println("Making move level \"hard\"");

        int optimalScore = (this.gamePiece == GameModel.PLAYER_X) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int[] coordinates = new int[2];

        for (int i = 0; i < this.gameTable.length; i++) {

            for (int j = 0; j < this.gameTable[i].length; j++) {

                if (this.gameTable[i][j] == ' ') {

                    this.gameTable[i][j] = this.gamePiece;

                    int score = miniMax(this.gameTable, isMaximizing(this.gamePiece));

                    this.gameTable[i][j] = ' ';

                    if (this.gamePiece == GameModel.PLAYER_X) {

                        if (score > optimalScore) {
                            optimalScore = score;
                            coordinates[x] = i;
                            coordinates[y] = j;
                        }

                    } else {

                        if (score < optimalScore) {
                            optimalScore = score;
                            coordinates[x] = i;
                            coordinates[y] = j;
                        }
                    }
                }
            }
        }

        this.model.updateGameTable(coordinates[x] + 1, coordinates[y] + 1, this.gamePiece);
    }

    private int miniMax(char[][] gameTable, boolean isMaximizing) {

        if (this.model.checkWinner(GameModel.PLAYER_X)) {
            return 1;
        }
        if (this.model.checkWinner(GameModel.PLAYER_O)) {
            return -1;
        }
        if (!this.model.checkWinner(GameModel.PLAYER_X) && !this.model.checkWinner(GameModel.PLAYER_O) &&
                countNumberOfPlays() == GameModel.MAX_NUMBER_OF_PLAYS) {
            return 0;
        }

        int optimalScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        if (isMaximizing) {

            for (int i = 0; i < gameTable.length; i++) {

                for (int j = 0; j < gameTable[i].length; j++) {

                    if (gameTable[i][j] == ' ') {

                        char currentPlayer = GameModel.PLAYER_X;

                        gameTable[i][j] = currentPlayer;

                        int score = miniMax(gameTable, isMaximizing(currentPlayer));

                        gameTable[i][j] = ' ';

                        optimalScore = Math.max(optimalScore, score);
                    }
                }
            }

            return optimalScore;

        } else {

            for (int i = 0; i < gameTable.length; i++) {

                for (int j = 0; j < gameTable[i].length; j++) {

                    if (gameTable[i][j] == ' ') {

                        char currentPlayer = GameModel.PLAYER_O;

                        gameTable[i][j] = currentPlayer;

                        int score = miniMax(gameTable, isMaximizing(currentPlayer));
                        gameTable[i][j] = ' ';

                        optimalScore = Math.min(optimalScore, score);
                    }
                }
            }
            return optimalScore;
        }
    }

    private boolean isMaximizing(char gamePiece) {
        return (gamePiece == GameModel.PLAYER_O);
    }

    private int countNumberOfPlays() {
        int counter = 0;
        for (char[] tab : this.gameTable) {
            for (char c : tab) {
                if (c == GameModel.PLAYER_X|| c == GameModel.PLAYER_O) {
                    counter++;
                }
            }
        }
        return counter;
    }
}