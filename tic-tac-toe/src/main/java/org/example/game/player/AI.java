package org.example.game.player;

import org.example.game.algorithm.GameAlgorithm;

class AI implements Player {

    private final GameAlgorithm algorithm;

    AI(GameAlgorithm algorithm) {
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
