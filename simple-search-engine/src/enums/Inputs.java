package enums;

public enum Inputs {

    FIND_PERSON(1),
    LIST_ALL(2),
    EXIT(0),
    INVALID_VALUE(3);

    private final int value;

    Inputs(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    public static Inputs getInput(int value) {
        for (Inputs inputs : Inputs.values()) {
            if (inputs.getValue() == value) {
                return inputs;
            }
        }
        return INVALID_VALUE;
    }
}
