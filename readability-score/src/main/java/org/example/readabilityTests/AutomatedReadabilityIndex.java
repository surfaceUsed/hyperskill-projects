package org.example.readabilityTests;

import org.example.util.Calculations;
import org.example.util.GradeLevel;

public class AutomatedReadabilityIndex implements ReadabilityTest {

    private static final String TEST_NAME = "Automated Readability Index";

    AutomatedReadabilityIndex() {}

    private double getTextScore(String text) {
        return 4.71 * ((double) Calculations.getAmountOfCharacters(text) /
                (double) Calculations.getAmountOfWords(text))
                + 0.5 * ((double) Calculations.getAmountOfWords(text) /
                (double) Calculations.getAmountOfSentences(text)) -
                21.43;
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
