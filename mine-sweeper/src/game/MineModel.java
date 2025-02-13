package game;

import java.util.Random;

/**
 * The MineModel class represents the underlying logic for a Minesweeper game.
 * It maintains the game board, mine placement, and mine threat calculations.
 */
public class MineModel {

    static final char SAFE_CELL = '.';
    static final char MINE = 'X';
    static final char MARKER = '*';
    static final char FREE_CELL = '/';

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    private final char[][] gameBoard = new char[ROWS][COLUMNS]; // Stores all game characters [X.*/].
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

    /**
     * Creates a mine board ensuring that the first move does not land on a mine.
     * @param row Initial safe row.
     * @param col Initial safe column.
     * @param numberOfMines Number of mines to place.
     */
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

    /**
     * Creates the board that stores the number of mines around each cell.
     */
    void createCountBoard() {
        for (int i = 0; i < this.mineBoard.length; i++) {
            for (int j = 0; j < this.mineBoard[i].length; j++) {
                if (this.mineBoard[i][j] != MINE) {
                    calculateMineThreat(i, j);
                }
            }
        }
    }

    /**
     * Calculates the number of mines surrounding a given cell.
     * @param row Row index.
     * @param col Column index.
     */
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
        } catch (IndexOutOfBoundsException ignored) {
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

    /**
     * Recursively reveals empty cells and their surroundings.
     * @param row Row index.
     * @param col Column index.
     */
    private void revealEmptyCell(int row, int col) {

        if (isOutOfBounds(row, col) || isFree(row, col) || isMarker(row, col)) {
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

    /**
     *
     * Method checks if the value on the given index is a marker (*). It will only stay a marker if there is a mine
     * under the same position. If it's marking a number or a free cell, it will be cleared, and replaced by either
     * of the two.
     */
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
