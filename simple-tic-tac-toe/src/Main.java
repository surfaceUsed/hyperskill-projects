import java.util.Scanner;

public class Main {
    static final int ROWS = 3;
    static final int COLUMNS = 3;
    static final char X = 'X';
    static final char O = 'O';
    static final char EMPTY = ' ';
    static char[][] board = new char[ROWS][COLUMNS];
    static Scanner scanner = new Scanner(System.in);
    static boolean xTurn = true; // True = 'X' plays, False = 'O' plays

    public static void main(String[] args) {
        initializeBoard();
        System.out.println(createGrid());
        playGame();
    }

    private static void playGame() {
        while (true) {
            getUserInput();
            int result = checkConditions();

            while (result != -1) {
                switch (result) {
                    case 1:
                        System.out.println("You should enter numbers!");
                        break;
                    case 2:
                        System.out.println("Coordinates should be from 1 to 3!");
                        break;
                    case 3:
                        System.out.println("This cell is occupied! Choose another one!");
                        break;
                }
                getUserInput();
                result = checkConditions();
            }

            System.out.println(createGrid());

            if (checkWin()) {
                System.out.println((xTurn ? "X" : "O") + " wins!");
                break;
            }

            if (isBoardFull()) {
                System.out.println("It's a tie!");
                break;
            }

            xTurn = !xTurn;
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void getUserInput() {
        System.out.print("Enter row and column (1-3): ");
    }

    private static int checkConditions() {
        String input = scanner.nextLine();
        String[] coordinates = input.split(" ");

        if (coordinates.length != 2) {
            return 1; // Invalid input format
        }
        if (!isDigit(coordinates)) {
            return 1; // Non-numeric input
        }
        int row = Integer.parseInt(coordinates[0]) - 1;
        int col = Integer.parseInt(coordinates[1]) - 1;
        if (!isValidCoordinate(row, col)) {
            return 2; // Out-of-bounds coordinates
        }
        if (!isEmpty(row, col)) {
            return 3; // Cell is occupied
        }

        board[row][col] = xTurn ? X : O; // Store move
        return -1; // Valid move
    }

    private static boolean isDigit(String[] coordinates) {
        try {
            Integer.parseInt(coordinates[0]);
            Integer.parseInt(coordinates[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    private static boolean isEmpty(int row, int col) {
        return board[row][col] == EMPTY;
    }

    private static boolean checkWin() {
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true; // Rows
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true; // Columns
        }
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true; // Diagonal \
        return board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]; // Diagonal /
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    private static String createGrid() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------\n");
        for (int i = 0; i < ROWS; i++) {
            sb.append("| ");
            for (int j = 0; j < COLUMNS; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("|\n");
        }
        sb.append("---------\n");
        return sb.toString();
    }
}
