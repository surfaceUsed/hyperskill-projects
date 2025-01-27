package org.example.game;

public enum GameState {

    STATE_START("start"),
    STATE_PLAYER_X_WINS("X"),
    STATE_PLAYER_O_WINS("O"),
    STATE_DRAW("Draw"),
    STATE_MAKE_PLAY("play"),
    STATE_EXIT("exit"),
    STATE_EASY("easy"),
    STATE_MEDIUM("medium"),
    STATE_HARD("hard"),
    STATE_USER("user");

    private final String message;

    GameState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public static GameState getState(String input) {
        for (GameState state : GameState.values()) {
            if (input.equals(state.getMessage())) {
                return state;
            }
        }
        return null;
    }
}
