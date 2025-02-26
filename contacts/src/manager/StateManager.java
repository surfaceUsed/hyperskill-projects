package manager;

import enums.State;

public class StateManager {

    private StateManager() {}

    private static State currentState = State.MENU;

    public static void setCurrentState(State state) {
        currentState = state;
    }

    public static State getCurrentState() {
        return currentState;
    }
}
