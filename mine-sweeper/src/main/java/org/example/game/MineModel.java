package org.example.game;

import java.util.Random;

public class MineModel {

    static final char SAFE_CELL = '.';
    static final char MINE = 'X';
    static final char MARKER = '*';
    static final char FREE_CELL = '/';

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    private final char[][] gameBoard = new char[ROWS][COLUMNS]; // Stores all game characters (X.*/).
    private final char[][] mineBoard = new char[ROWS][COLUMNS]; // Separate board that holds all the mine placements.
    private final int[][] countMineThreat = new int[ROWS][COLUMNS]; // Separate board that holds all the mine-counter values.

    {
        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j =0; j < this.gameBoard[i].length; j++) {
                this.gameBoard[i][j] = SAFE_CELL;
            }
        }
    }

    MineModel() {}

    char[][] getGameBoard() {
        return this.gameBoard;
    }

    char[][] getMineBoard() {
        return this.mineBoard;
    }

    boolean isMine(int row, int col) {
        return this.mineBoard[row][col] == MINE;
    }

    void updateGameBoard(int row, int col, char input) {
        this.gameBoard[row][col] = input;
    }

    // Creates a mine board that is used to check every gameplay up against.
    // Since a player has to be able to make the first move without stepping on a mine, we need to make sure that
    // the first indexes chosen are safe.
    void createMineBoard(int row, int col, int numberOfMines) {

        Random random = new Random();
        int placedMines = 0;

        while (placedMines < numberOfMines) {

            int rowTest = random.nextInt(ROWS);
            int colTest = random.nextInt(COLUMNS);

            if (!isMine(rowTest, colTest) && (rowTest != row || colTest != col)) {
                this.mineBoard[rowTest][colTest] = MINE;
                placedMines++;
            }
        }
    }

    // A separate game board that holds all the values for mine-threats in the area.
    void createCountBoard() {

        for (int i = 0; i < this.mineBoard.length; i++) {
            for (int j = 0; j < this.mineBoard[i].length; j++) {
                if (this.mineBoard[i][j] != MINE) {
                    calculateMineThreat(i, j);
                }
            }
        }
    }

    // Method counts the number of mines that are in the area for each index in the table, and saves the values
    // in the "countMineThreat"-table. Like the "mineBoard"-table; this table is also used to compare up
    // against after every single play.
    private void calculateMineThreat(int row, int col) {

        int counter = placeCounter(row - 1, col - 1) + placeCounter(row - 1, col) +
                placeCounter(row - 1, col + 1) + placeCounter(row, col - 1) + placeCounter(row, col + 1) +
                placeCounter(row + 1, col - 1) + placeCounter(row + 1, col) + placeCounter(row + 1, col + 1);

        this.countMineThreat[row][col] = counter;
    }

    private int placeCounter(int row, int col) {
        try {
            if (this.mineBoard[row][col] == MINE) {
                return 1;
            }
        } catch (IndexOutOfBoundsException e) {
            // coordinates out of bounds.
        }
        return 0;
    }

    // This method handles the players move on the game board.
    void makePlay(int row, int col) {

        if (isFree(row, col)) {
            System.out.println("This cell is marked as free!");

        } else {

            if (this.gameBoard[row][col] != SAFE_CELL && this.gameBoard[row][col] != MARKER) {
                System.out.println("There is a number here!");

            } else {

                revealEmptyCell(row, col);
            }
        }
    }

    // A recursive method that frees up every cell that is free, and stops when there are no more empty cells to free
    // up (when it reaches the end of the gameboard (top, bottom, sides) or when it reaches a hidden number that
    // indicates there is a mine lying around).
    private void revealEmptyCell(int row, int col) {

        if (isOutOfBounds(row, col)) {
            return;
        }

        if (isFree(row, col)) {
            return;
        }

        if (isMarker(row, col)) {
            return;
        }

        if (isNumber(row, col)) {
            updateGameBoard(row, col, Character.forDigit(this.countMineThreat[row][col], 10));
            return;
        }

        if (this.countMineThreat[row][col] == 0) {
            updateGameBoard(row, col, FREE_CELL);
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    // recursive call; checks the index-cell around every FREE_CELL (works like the calculateMineThreat()-method).
                    revealEmptyCell(i, j);
                }
            }

        } else {

            updateGameBoard(row, col, Character.forDigit(this.countMineThreat[row][col], 10));
        }
    }

    private boolean isOutOfBounds(int row, int col) {
        return ((row < 0 || row >= this.gameBoard.length) || (col < 0 || col >= this.gameBoard.length));
    }

    private boolean isFree(int row, int col) {
        return (this.gameBoard[row][col] == FREE_CELL);
    }

    // Method checks if the value on the given index is a marker (*). It will only stay a marker if there is a mine
    // under the same position. If it's marking a number or a free cell, it will be cleared, and replaced by either
    // of the two.
    private boolean isMarker(int row, int col) {

        if (this.gameBoard[row][col] == MARKER) {
            if (this.mineBoard[row][col] == MINE) {
                return true;

            } else if (this.countMineThreat[row][col] > 0) {
                this.gameBoard[row][col] = Character.forDigit(this.countMineThreat[row][col], 10);

            } else {
                this.gameBoard[row][col] = FREE_CELL;
            }
        }

        return false;
    }

    private boolean isNumber(int row, int col) {
        return (this.countMineThreat[row][col] > 0);
    }
}
