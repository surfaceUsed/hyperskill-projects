package org.example.game;

import org.example.game.player.Player;
import org.example.game.player.PlayerCreator;

public class GameModel {

    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';

    public static final int MAX_NUMBER_OF_PLAYS = 9;

    private final char[][] gameTable = {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    private int numberOfPlays = 0;
    private Player playerOne;
    private Player playerTwo;

    public GameModel() {}

    public char[][] getGameTable() {
        return this.gameTable;
    }

    void setPlayers(String playerOne, String playerTwo) {

        this.playerOne = PlayerCreator.createPlayer(playerOne, PLAYER_X, this);
        this.playerTwo = PlayerCreator.createPlayer(playerTwo, PLAYER_O, this);
    }

    GameState play() {

        Player player = getPlayerTurn();
        player.makePlay();
        this.numberOfPlays++;

        if (checkWinner(player.getGamePiece())) {
            return GameState.getState(String.valueOf(player.getGamePiece()));

        } else if (this.numberOfPlays == MAX_NUMBER_OF_PLAYS && !checkWinner(player.getGamePiece())) {
            return GameState.STATE_DRAW;
        }

        return GameState.STATE_MAKE_PLAY;
    }

    private Player getPlayerTurn() {
        return (this.numberOfPlays % 2 == 0) ? this.playerOne : this.playerTwo;
    }


    public boolean checkWinner(char playerPiece) {
        return checkHorizontal(playerPiece) || checkVertical(playerPiece) || checkDiagonal(playerPiece);
    }

    private boolean checkHorizontal(char playerPiece) {

        for (char[] chars : this.gameTable) {

            int column = 0;

            if (playerPiece == chars[column] && playerPiece == chars[++column] &&
                    playerPiece == chars[++column]) {

                return true;
            }
        }
        return false;
    }

    private boolean checkVertical(char playerPiece) {

        int indexColumn = 0;

        for (int i = 0; i < this.gameTable.length; i++) {

            int indexRow = 0;

            if (playerPiece == this.gameTable[indexRow][indexColumn] &&
                    playerPiece == this.gameTable[++indexRow][indexColumn] &&
                    playerPiece == this.gameTable[++indexRow][indexColumn]) {

                return true;
            }
            indexColumn++;
        }
        return false;
    }

    private boolean checkDiagonal(char playerPiece) {

        int rowMidValue = this.gameTable.length / 2, columnMidValue = this.gameTable[1].length / 2;
        char midValue = this.gameTable[rowMidValue][columnMidValue];

        if (playerPiece == midValue) {

            return (playerPiece == this.gameTable[rowMidValue - 1][columnMidValue - 1] &&
                    playerPiece == this.gameTable[rowMidValue + 1][columnMidValue + 1]) ||
                    (playerPiece == this.gameTable[rowMidValue - 1][columnMidValue + 1] &&
                            playerPiece == this.gameTable[rowMidValue + 1][columnMidValue - 1]);
        }

        return false;
    }

    void prepareForNewGame() {

        for (int i = 0; i < this.gameTable.length; i++) {
            for (int j = 0; j < this.gameTable[i].length; j++) {
                this.gameTable[i][j] = ' ';
            }
        }
        this.numberOfPlays = 0;
    }

    public void updateGameTable(int x, int y, char gamePiece) {
        this.gameTable[x- 1][y - 1] = gamePiece;
    }

    public char getOpponentGamePiece(char playerGamePiece) {
        return (playerGamePiece == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }
}