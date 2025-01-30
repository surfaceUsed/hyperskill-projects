import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static final char X = 'X';
    static final char O = 'O';
    static final int ROWS = 3;
    static final int COLUMNS = 3;
    static final int PICK_COORDINATES = 2;
    static char[][] table = new char[ROWS][COLUMNS];
    static String[] coordinates = new String[PICK_COORDINATES];

    public static void main(String[] args) {

        playGame();
    }

    public static void playGame() {
        firstInput();
        secondInput();
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
                default:
                    break;
            }
            secondInput();
            result = checkConditions();
        }
        placeX();
        System.out.println(createGrid());
    }

    private static void firstInput() {
        createTwoDimArray(scanner.nextLine().toCharArray());
        System.out.println(createGrid());
    }

    private static void secondInput() {
        coordinates = scanner.nextLine().split(" ");
    }

    private static int checkConditions() {
        if (!isDigit()) {
            return 1; // You should enter numbers.
        }
        if (!isValidCoordinate()) {
            return 2; // Coordinates should be from 1 to 3.
        }
        if (!isEmpty()) {
            return 3; // This cell is occupied! Choose another one!
        }
        return -1;
    }

    private static void createTwoDimArray(char[] arr) {
        int index = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                table[i][j]= arr[index];
                index++;
            }
        }
    }

    private static String createGrid() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------").append("\n");
        for (int i = 0; i < ROWS; i++) {
            sb.append("| ");
            for (int j = 0; j < COLUMNS; j++) {
                sb.append(table[i][j]).append(" ");
            }
            sb.append("|").append("\n");
        }
        sb.append("---------").append("\n");
        return sb.toString();
    }

    private static boolean isDigit() {
        try {
            for (String s : coordinates) {
                Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidCoordinate() {
        for (String s : coordinates) {
            int numb = Integer.parseInt(s);
            if (numb < 1 || numb > 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean isEmpty() {
        int xCoordinate = Integer.parseInt(coordinates[0]) - 1;
        int yCoordinate = Integer.parseInt(coordinates[1]) - 1;
        boolean isX = table[xCoordinate][yCoordinate] == X;
        boolean isY = table[xCoordinate][yCoordinate] == O;
        return !isX && !isY;
    }

    private static void placeX() {
        int xCoordinate = Integer.parseInt(coordinates[0]) - 1;
        int yCoordinate = Integer.parseInt(coordinates[1]) - 1;
        table[xCoordinate][yCoordinate] = X;
    }

}