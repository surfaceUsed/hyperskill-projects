package org.example.readabilityTests;

import org.example.util.Calculations;
import org.example.util.GradeLevel;

public class ColemanLiauIndex implements ReadabilityTest {

    private static final String TEST_NAME = "Coleman–Liau index";

    ColemanLiauIndex() {}

    private double getTextScore(String text) {
        return (0.0588 * Calculations.getL(text)) - (0.296 * Calculations.getS(text)) - 15.8;
    }

    @Override
    public int getGradeLevel(String text) {
        return GradeLevel.getAgeLevel((int) Math.ceil(getTextScore(text)));
    }

    @Override
    public String getResult(String text) {
        return String.format("%s: %.2f (about %d-year-olds)",TEST_NAME, getTextScore(text), getGradeLevel(text));
    }
}
