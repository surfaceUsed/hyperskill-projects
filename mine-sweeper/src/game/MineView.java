package game;

public class MineView {

    MineView() {}

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

    void gameInstructions() {
        System.out.println();
        System.out.println("To make a move, enter the coordinates followed by the type of action.");
        System.out.println("First, enter the row number (y-coordinate), then the column number (x-coordinate).");
        System.out.println("After the coordinates, specify the action:");
        System.out.println("\n  - Type 'mine' to mark a potential mine.");
        System.out.println("  - Type 'free' to uncover the cell.\n");
        System.out.println("Example: '3 5 free' will uncover the cell at row 3, column 5.");
        System.out.print("Set/unset mine marks or claim a cell as free (y-coordinate x-coordinate mine/free): ");
    }
}
