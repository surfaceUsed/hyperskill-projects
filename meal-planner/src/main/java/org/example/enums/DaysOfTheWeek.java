package org.example.enums;

public enum DaysOfTheWeek {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String day;

    DaysOfTheWeek(String day) {
        this.day = day;
    }

    public String getDay() {
        return this.day;
    }

    public static DaysOfTheWeek getDayOfWeek(String dayName) {
        for (DaysOfTheWeek day : DaysOfTheWeek.values()) {
            if (day.getDay().equals(dayName)) {
                return day;
            }
        }
        return null;
    }
}

