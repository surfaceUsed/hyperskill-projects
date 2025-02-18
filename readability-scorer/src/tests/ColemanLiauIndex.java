package tests;

import enums.GradeLevel;
import enums.TestType;
import logic.Calculations;

public class ColemanLiauIndex implements ReadabilityTest {

    ColemanLiauIndex() {}

    private double getTextScore(String text) {
        return (0.0588 * Calculations.getAverageLetterCount(text)) - (0.296 * Calculations.getAverageSentenceCount(text)) - 15.8;
    }

    @Override
    public int getGradeLevel(String text) {
        return GradeLevel.getAgeLevel((int) Math.ceil(getTextScore(text)));
    }

    @Override
    public String getResult(String text) {
        return String.format("%s: %.2f (about %d-year-olds)", TestType.CL.getTestName(), getTextScore(text), getGradeLevel(text));
    }
}
