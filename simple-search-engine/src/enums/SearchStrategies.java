package enums;

public enum SearchStrategies {

    ALL("all"), // Searches for lines in the input text that contain all the search keys.
    ANY("any"), // Searches for lines in the input text that contains at least one of the search keys.
    NONE("none"), // Searches for lines in the input text that contains none of the search keys.
    INVALID("invalid strategy key");

    private final String key;

    SearchStrategies(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static SearchStrategies getStrategy(String input) {
        for (SearchStrategies strategy : SearchStrategies.values()) {
            if (strategy.getKey().equalsIgnoreCase(input)) {
                return strategy;
            }
        }
        return INVALID;
    }
}
