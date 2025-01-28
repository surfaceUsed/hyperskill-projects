package org.example.game;

class MineView {

    MineView() {

    }

    void printGameBoard(char[][] gameBoard) {
        System.out.println(" | 1 2 3 4 5 6 7 8 9 |");
        System.out.println("-| - - - - - - - - - |");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(" " + gameBoard[i][j]);
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("-| - - - - - - - - - |");
    }

    void printGameOverBoard(char[][] gameBoard, char[][] mineBoard) {
        System.out.println(" | 1 2 3 4 5 6 7 8 9 |");
        System.out.println("-| - - - - - - - - - |");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (mineBoard[i][j] == 'X') {
                    System.out.print(" " + mineBoard[i][j]);
                } else {
                    System.out.print(" " + gameBoard[i][j]);
                }
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("-| - - - - - - - - - |");
    }
}
