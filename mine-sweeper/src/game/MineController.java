package game;

import java.util.Scanner;

public class MineController {

    private static final String STATE_FREE = "free";
    private static final String STATE_MINE = "mine";
    private static final String STATE_INVALID = "invalid";

    private final MineModel model;
    private final MineView view;
    private final int numberOfMines;
    private final Scanner scanner;

    private boolean isFinished;
    private boolean isFirstPlay;
    private int mineMarkerHit;
    private int numberOfMineMarkers;
    private int rowIndex;
    private int columnIndex;
    private String action;

    public MineController(int numberOfMines) {
        this.model = new MineModel();
        this.view = new MineView();
        this.numberOfMines = numberOfMines;
        this.scanner = new Scanner(System.in);
        this.isFinished = false;
        this.isFirstPlay = true;
        this.mineMarkerHit = 0;
        this.numberOfMineMarkers = 0;
    }

    /**
     * Starts the game loop and processes player input.
     */
    public void playGame() {

        this.view.gameInstructions();
        String[] input = this.scanner.nextLine().split(" ");
        parseInput(input);
        //this.view.printGameBoard(this.model.getGameBoard());

        while (!isFinished) {

            switch (this.action) {

                case STATE_FREE:
                    placeFreeMarker(this.rowIndex, this.columnIndex);
                    break;

                case STATE_MINE:
                    placeMineMarker(this.rowIndex, this.columnIndex);
                    break;

                case STATE_INVALID:
                    System.out.println("Try again.");
                    break;
            }

            if (!isFinished) {
                System.out.print("Set/unset mine marks, or claim a cell as free: ");
                input = this.scanner.nextLine().split(" ");
                parseInput(input);
            }
        }
        this.scanner.close();
    }

    /**
     * Parses user input and determines action type.
     * @param input Array containing row, column, and action.
     */
    private void parseInput(String[] input) {
        if (input.length != 3) {
            System.out.println("\nInput malformed. Need to be in the format \"'row' 'column' 'action\".\n");
            this.action = STATE_INVALID;
        } else {

            try {
                this.rowIndex = Integer.parseInt(input[0]) - 1;
                this.columnIndex = Integer.parseInt(input[1]) - 1;
            } catch (NumberFormatException ignored) {
                System.out.println("\nInvalid coordinate input!\n");
                this.action = STATE_INVALID;
                return;
            }

            String action = input[2];
            if (!action.equals(STATE_FREE) && !action.equals(STATE_MINE)) {
                System.out.println("\n" + action + " is not a valid action.\n");
                this.action = STATE_INVALID;
                return;
            }
            this.action = input[2];
        }
    }

    /**
     * Handles revealing a cell.
     * @param row Row index of the cell.
     * @param col Column index of the cell.
     */
    private void placeFreeMarker(int row, int col) {
        // If it's the first move, generate the minefield ensuring the first play is safe
        if (isFirstPlay) {
            this.model.createMineBoard(row, col, this.numberOfMines);
            this.model.createCountBoard();
            this.model.makePlay(row, col);
            this.view.printGameBoard(this.model.getGameBoard());
            isFirstPlay = false;

        } else {

            if (this.model.isMine(row, col)) {
                this.view.printGameOverBoard(this.model.getGameBoard(), this.model.getMineBoard());
                System.out.println("You stepped on a mine and failed!");
                this.isFinished = true;

            } else {
                this.model.makePlay(row, col);
                this.view.printGameBoard(this.model.getGameBoard());
                checkIfOnlyMinesLeft();
            }
        }
    }

    /**
     * Handles placing or removing a mine marker.
     * @param row Row index of the cell.
     * @param col Column index of the cell.
     */
    private void placeMineMarker(int row, int col) {

        char cell = this.model.getGameBoard()[row][col];

        if (isFirstPlay) {
            this.model.createMineBoard(row, col, this.numberOfMines);
            this.model.createCountBoard();
            this.isFirstPlay = false;
        }

        if (cell == MineModel.SAFE_CELL) {
            this.model.updateGameBoard(row, col, MineModel.MARKER);
            this.numberOfMineMarkers++;
            if (this.model.isMine(row, col)) {
                this.mineMarkerHit++;
            }

        } else {

            if (cell == MineModel.MARKER) {
                this.model.updateGameBoard(row, col, MineModel.SAFE_CELL);
                this.numberOfMineMarkers--;
                if (this.model.isMine(row, col)) {
                    this.mineMarkerHit--;
                }
            }
        }

        this.view.printGameBoard(this.model.getGameBoard());

        isFinished = ((this.mineMarkerHit == this.numberOfMines) &&
                (this.mineMarkerHit == this.numberOfMineMarkers));

        if (isFinished) {
            System.out.println("Congratulations! You found all the mines!");
        }
    }

    private void checkIfOnlyMinesLeft() {

        char[][] table = this.model.getGameBoard();
        int counter = 0;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == MineModel.SAFE_CELL) {
                    counter++;
                }
            }
        }
        if (this.numberOfMines == counter) {
            isFinished = true;
            System.out.println("Congratulations! You found all the mines!");
        }
    }
}
