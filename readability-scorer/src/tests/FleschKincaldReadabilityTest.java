package tests;

import enums.GradeLevel;
import enums.TestType;
import logic.Calculations;

public class FleschKincaldReadabilityTest implements ReadabilityTest {

    FleschKincaldReadabilityTest() {}

    private double getTextScore(String text) {
        return 0.39 * ((double) Calculations.getAmountOfWords(text) /
                (double) Calculations.getAmountOfSentences(text)) +
                11.8 * ((double) Calculations.getAmountOfSyllables(text) /
                        (double) Calculations.getAmountOfWords(text)) - 15.59;
    }

    @Override
    public int getGradeLevel(String text) {
        return GradeLevel.getAgeLevel((int) Math.ceil(getTextScore(text)));
    }

    @Override
    public String getResult(String text) {
        return String.format("%s: %.2f (about %d-year-olds)", TestType.FK.getTestName(), getTextScore(text), getGradeLevel(text));
    }
}
