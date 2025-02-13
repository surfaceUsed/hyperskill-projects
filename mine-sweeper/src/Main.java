import game.MineController;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Input number of mines in game: ");
        MineController controller = new MineController(SCANNER.nextInt());
        controller.playGame();
        SCANNER.close();
    }
}