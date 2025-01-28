package org.example.game;

import java.util.Scanner;

public class MineController {

    private final static String STATE_FREE = "free";
    private final static String STATE_MINE = "mine";

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

    public void playGame() {

        this.view.printGameBoard(this.model.getGameBoard());
        System.out.print("Set/unset mines marks or claim a cell as free: ");
        String[] input = this.scanner.nextLine().split(" ");
        parseInput(input);

        while (!isFinished) {

            switch (this.action) {

                case STATE_FREE:
                    placeFreeMarker(this.rowIndex, this.columnIndex);
                    break;

                case STATE_MINE:
                    placeMineMarker(this.rowIndex, this.columnIndex);
                    break;

            }

            if (!isFinished) {
                System.out.print("Set/unset mines marks or claim a cell as free: ");
                input = this.scanner.nextLine().split(" ");
                parseInput(input);
            }
        }
        this.scanner.close();
    }

    private void parseInput(String[] input) {
        this.rowIndex = Integer.parseInt(input[0]) - 1;
        this.columnIndex = Integer.parseInt(input[1]) - 1;
        this.action = input[2];
    }

    // Handles freeing up cells.
    private void placeFreeMarker(int row, int col) {

        // If it's the first play the player makes, the mine- and counter-tables are created.
        // After that the gameBoard gets updated.
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

    // Method keeps track of mine markers. If every mine has been marked, the game is finished, and the player wins,
    // but if there are more markers than there are mines, the player needs to remove some markers, or keep trying to
    // free up space until there are only mines left.
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
