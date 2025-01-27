package org.example.readabilityTests;


import org.example.util.Calculations;
import org.example.util.GradeLevel;

public class SMOGIndex implements ReadabilityTest {

    private static final String TEST_NAME = "Simple Measure of Gobbledygook";

    SMOGIndex() {}

    private double getTextScore(String text) {
        double sumToSquare = Calculations.getPolysyllables(text) * (30.0 /
                (double) Calculations.getAmountOfSentences(text));
        return 1.043 * (Math.sqrt(sumToSquare)) + 3.1291;
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
