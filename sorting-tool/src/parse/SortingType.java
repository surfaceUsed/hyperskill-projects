package parse;

public enum SortingType {

    NATURAL("natural"),
    BY_COUNT("byCount"),
    INVALID("invalid");

    private final String type;

    SortingType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SortingType getType(String input) {
        for (SortingType type : SortingType.values()) {
            if (type.getType().equalsIgnoreCase(input)) {
                return type;
            }
        }
        return INVALID;
    }
}

