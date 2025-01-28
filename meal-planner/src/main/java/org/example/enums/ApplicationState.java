package org.example.enums;

public enum ApplicationState {

    STATE_START("start"),
    STATE_ADD("add"),
    STATE_SHOW("show"),
    STATE_PLAN("plan"),
    STATE_SAVE("save"),
    STATE_EXIT("exit"),
    STATE_INVALID("invalid inut");

    private final String input;

    ApplicationState(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public static ApplicationState getState(String input) {
        for (ApplicationState state : ApplicationState.values()) {
            if (state.getInput().equals(input)) {
                return state;
            }
        }
        return STATE_INVALID;
    }
}
