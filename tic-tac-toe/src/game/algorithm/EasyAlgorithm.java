package game.algorithm;

import game.GameModel;
import java.util.Random;

public class EasyAlgorithm implements GameAlgorithm {

    private final char gamePiece;
    private final GameModel model;
    private final char[][] gameTable;

    public EasyAlgorithm(GameModel model, char gamePiece) {
        this.model = model;
        this.gamePiece = gamePiece;
        this.gameTable = this.model.getGameTable();
    }

    @Override
    public char getGamePiece() {
        return this.gamePiece;
    }

    @Override
    public void gameLogic() {
        makeEasyPlay();
    }

    private void makeEasyPlay() {
        Random random = new Random();
        int x;
        int y;

        System.out.println("Making move level \"easy\"");

        do {

            x = random.nextInt(3) + 1;
            y = random.nextInt(3) + 1;

        } while (this.gameTable[x - 1][y - 1] == GameModel.PLAYER_X || this.gameTable[x - 1][y - 1] == GameModel.PLAYER_O);

        this.model.updateGameTable(x, y, this.gamePiece);
    }
}