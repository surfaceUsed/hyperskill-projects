package tests;

import enums.GradeLevel;
import enums.TestType;
import logic.Calculations;

public class SMOGIndex implements ReadabilityTest {

    SMOGIndex() {}

    private double getTextScore(String text) {
        double sumToSquare = Calculations.getPolySyllables(text) * (30.0 /
                (double) Calculations.getAmountOfSentences(text));
        return 1.043 * (Math.sqrt(sumToSquare)) + 3.1291;
    }

    @Override
    public int getGradeLevel(String text) {
        return GradeLevel.getAgeLevel((int) Math.ceil(getTextScore(text)));
    }

    @Override
    public String getResult(String text) {
        return String.format("%s: %.2f (about %d-year-olds)", TestType.SMOG.getTestName(), getTextScore(text), getGradeLevel(text));
    }
}
