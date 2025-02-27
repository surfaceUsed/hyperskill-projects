package util;

public final class ValidationConstants {

    public static final String INVALID_NUMBER = "[no number]";
    public static final String INVALID_INPUT = "[no data]";
    public static final String MALE = "M";
    public static final String FEMALE = "F";
    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String PHONE_NUMBER_VALIDATOR =
            "^(?i)[+]?((([(][\\dA-Za-z]{1,}[)][\\s-]?)?([\\dA-Za-z]{2,}[\\s-]?)*)$|" +
                    "(([\\dA-Za-z]{1,}[\\s-]?)?([(][\\dA-Za-z]{2,}[)][\\s-]?)?([\\dA-Za-z]{2,}[\\s-]?)*))$";

    private ValidationConstants() {}
}
