package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class InputValidator {

    private InputValidator() {}

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(ValidationConstants.PHONE_NUMBER_VALIDATOR);
    }

    public static boolean isValidGender(String gender) {
        return (gender.equals(ValidationConstants.MALE) || gender.equals(ValidationConstants.FEMALE));
    }

    public static boolean isValidBirthDate(String birthDay) {

        String europeanDateFormat = ValidationConstants.DATE_FORMAT;
        SimpleDateFormat formatter = new SimpleDateFormat(europeanDateFormat);
        formatter.setLenient(false); // Checks if date is actually valid (example invalid - 31.02.1999).

        try {
            Date date = formatter.parse(birthDay);
            return birthDay.equals(formatter.format(date));
        } catch (ParseException ignored) {}

        return false;
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ignored) {}
        return false;
    }
}