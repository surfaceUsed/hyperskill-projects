package game.player;

import game.algorithm.GameAlgorithm;

class User implements Player {

    private final GameAlgorithm algorithm;

    User(GameAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public char getGamePiece() {
        return this.algorithm.getGamePiece();
    }

    @Override
    public void makePlay() {
        this.algorithm.gameLogic();
    }
}
