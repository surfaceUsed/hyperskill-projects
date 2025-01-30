import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final char EMPTY = 'S';
    private static final char BOOKED = 'B';

    private static final int PRICE_ONE = 10;
    private static final int PRICE_TWO = 8;

    private static final String SEATS = "1. Show the seats";
    private static final String BUY_TICKET = "2. Buy a ticket";
    private static final String STATISTICS = "3. Statistics";
    private static final String EXIT = "0. Exit";

    private static char[][] cinemaView;

    private static int rows;
    private static int columns;

    private static int totalNumberOfSeats;
    private static int totalBookings = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {
        start();
    }

    private static void start() {

        createCinema();
        menu();
        int input = scanner.nextInt();

        while (input != 0) {

            switch (input) {

                case 1:
                    System.out.println(createView());
                    break;

                case 2:
                    pickSeat();
                    break;

                case 3:
                    precentStatistics();
                    break;

                default:
                    break;
            }

            menu();
            input = scanner.nextInt();
        }

        scanner.close();
    }

    private static void menu() {

        System.out.println(SEATS);
        System.out.println(BUY_TICKET);
        System.out.println(STATISTICS);
        System.out.println(EXIT);
    }

    private static double percentageSeatsTaken() {
        return ((double)totalBookings / (double)totalNumberOfSeats) * 100.0;
    }

    private static void createCinema() {

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        totalNumberOfSeats = rows * columns;
        cinemaView = createArray(rows, columns);
        calculateTotalIncome();
    }

    private static void precentStatistics() {

        String stat = String.format("""
                Number of purchased tickets: %d
                Percentage: %.2f%s
                Current income: $%d
                Total income: $%d""", totalBookings, percentageSeatsTaken(), "%", currentIncome, totalIncome);

        System.out.println(stat);
    }

    private static void pickSeat() {

        while (true) {

            System.out.println("Enter a row number:");
            int rowNumb = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumb = scanner.nextInt();

            if (!checkInput(rowNumb, seatNumb)) {
                System.out.println("Wrong input!");

            } else {

                if (isEmpty(rowNumb, seatNumb)) {
                    cinemaView[rowNumb - 1][seatNumb - 1] = BOOKED;
                    totalBookings++;
                    currentIncome += calculatePrice(rowNumb);
                    System.out.println("\nTicket price: $" + calculatePrice(rowNumb) + "\n");
                    return;

                } else {

                    System.out.println("That ticket has already been purchased!");
                }
            }
        }
    }

    private static boolean isEmpty(int row, int seat) {
        return (cinemaView[row-1][seat-1] == EMPTY);
    }

    private static boolean checkInput(int row, int seat) {
        return (row <= rows && row > 0) && (seat <= columns && seat > 0);
    }

    private static int calculatePrice(int row) {

        if (totalNumberOfSeats <= 60) {
            return PRICE_ONE;

        } else {

            int firstRows = rows / 2;
            return (row <= firstRows) ? PRICE_ONE : PRICE_TWO;
        }
    }

    private static void calculateTotalIncome() {

        if (totalNumberOfSeats <= 60) {
            totalIncome = totalNumberOfSeats * 10;

        } else {

            int firstSection = rows / 2;
            int secondSection = rows - firstSection;
            totalIncome = (firstSection * columns) * PRICE_ONE + (secondSection * columns) * PRICE_TWO;
        }
    }

    private static char[][] createArray(int rows, int seatsInRow) {
        char[][] arr = new char[rows][seatsInRow];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = EMPTY;
            }
        }
        return arr;
    }

    private static String createView() {
        StringBuilder sb = new StringBuilder(createTopView());
        for (int i = 0; i < cinemaView.length; i++) {
            sb.append("\n").append(i + 1);
            for (int j = 0; j < cinemaView[i].length; j++) {
                sb.append(" ").append(cinemaView[i][j]);
            }
        }
        return sb.append("\n").toString();
    }

    private static String createTopView() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cinema:").append("\n").append("  ");
        for (int i = 1; i < columns; i++) {
            sb.append(i).append(" ");
        }
        return sb.append(columns).toString();
    }
}