package game.player;

import game.GameModel;
import game.GameState;
import game.algorithm.*;

public abstract class PlayerCreator {

    public static Player createPlayer(String playerInfo, char gamePiece, GameModel model) {
        GameState state = GameState.getState(playerInfo);

        if (state != null) {

            GameAlgorithm algorithm;
            switch (state) {

                case STATE_EASY:
                    algorithm = new EasyAlgorithm(model, gamePiece);
                    return new AI(algorithm);

                case STATE_MEDIUM:
                    algorithm = new MediumAlgorithm(model, gamePiece);
                    return new AI(algorithm);

                case STATE_HARD:
                    algorithm = new HardAlgorithm(model, gamePiece);
                    return new AI(algorithm);

                case STATE_USER:
                    algorithm = new UserAlgorithm(model, gamePiece);
                    return new User(algorithm);

                default:
                    break;
            }
        }
        return null;
    }
}
