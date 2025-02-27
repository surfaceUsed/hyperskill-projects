package enums;

public enum State {

    MENU,
    ADD,
    LIST,
    EDIT,
    SEARCH,
    COUNT,
    EXIT,
    BACK,
    AGAIN,
    UPDATE,
    DELETE,
    INVALID_STATE;

    public static State getState(String input) {
        for (State state : State.values()) {
            if (state.name().equals(input.toUpperCase().trim())) {
                return state;
            }
        }
        return INVALID_STATE;
    }
}
