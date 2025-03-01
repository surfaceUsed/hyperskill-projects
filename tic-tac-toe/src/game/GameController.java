package game;

import java.util.Scanner;

public class GameController {

    private final GameModel model;
    private final GameView view;
    private final Scanner scanner = new Scanner(System.in);

    private GameState state;
    private boolean isFinished = false;
    private boolean printGrid = true;

    public GameController() {
        this.model = new GameModel();
        this.view = new GameView();
        this.state = GameState.STATE_START;
    }

    public void playGame() {

        updateGameState(this.state);
    }

    private void updateGameState(GameState state) {

        while (!this.isFinished) {

            switch (state) {

                case STATE_START:
                    start();
                    break;

                case STATE_MAKE_PLAY:
                    makePlay();
                    break;

                case STATE_PLAYER_X_WINS:
                    System.out.println("X wins");
                    prepareNewGame();
                    break;

                case STATE_PLAYER_O_WINS:
                    System.out.println("O wins");
                    prepareNewGame();
                    break;

                case STATE_DRAW:
                    System.out.println("draw");
                    prepareNewGame();
                    break;

                case STATE_EXIT:
                    exit();
                    break;
            }

            if (this.printGrid) {

                System.out.println(this.view.createGrid(this.model.getGameTable()));
            }

            state = this.state;
        }
    }

    private void start() {

        System.out.print("Input command: ");
        String[] input = this.scanner.nextLine().split(" ");

        if ((input.length == 1) && (GameState.getState(input[input.length - 1]) == GameState.STATE_EXIT)) {

            this.printGrid = false;
            this.state = GameState.STATE_EXIT;

        } else {
            if ((input.length == 3) && ((GameState.getState(input[0]) == GameState.STATE_START) &&
                    (GameState.getState(input[1]) == GameState.STATE_USER) || (GameState.getState(input[1]) == GameState.STATE_EASY) ||
                    (GameState.getState(input[1]) == GameState.STATE_MEDIUM) || (GameState.getState(input[1]) == GameState.STATE_HARD)) &&
                    ((GameState.getState(input[2]) == GameState.STATE_USER) || (GameState.getState(input[2]) == GameState.STATE_EASY) ||
                            (GameState.getState(input[2]) == GameState.STATE_MEDIUM) || (GameState.getState(input[2]) == GameState.STATE_HARD))) {

                this.model.setPlayers(input[1], input[2]);
                this.printGrid = true;
                this.state = GameState.STATE_MAKE_PLAY;

            } else {

                System.out.println("Bad parameters!");
                this.printGrid = false;
                this.state = GameState.STATE_START;
            }
        }
    }

    private void makePlay() {
        this.state = this.model.play();
        printGrid = true;
    }

    private void prepareNewGame() {
        this.model.prepareForNewGame();
        this.state = GameState.STATE_START;
        this.printGrid = false;
    }

    private void exit() {
        this.state = GameState.STATE_EXIT;
        this.printGrid = false;
        this.isFinished = true;
        this.scanner.close();
    }
}
