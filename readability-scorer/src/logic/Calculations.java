package logic;

import enums.TestType;
import tests.TestFactory;

public class Calculations {

    private static final String REGEX_SENTENCES = "[.!?]+\\s*"; // Splits sentences.
    private static final String REGEX_WORDS = "[\\s.!?]+"; // Splits words.
    private static final String REGEX_CHARACTERS = "[\\s]"; // Splits characters.
    private static final String CONSONANTS ="bcdfghjklmnpqrstvwxz";
    private static final String VOWELS = "aeiouy";
    private static final char SPECIAL_CASE_VOWEL = 'y';

    private Calculations() {}

    public static int getAmountOfSentences(String text) {
        return text.split(REGEX_SENTENCES).length;
    }

    public static int getAmountOfWords(String text) {
        return text.split(REGEX_WORDS).length;
    }

    public static int getAmountOfCharacters(String text) {
        return text.replaceAll(REGEX_CHARACTERS, "").length();
    }

    // The average number of letters per 100 words in the text.
    public static double getAverageLetterCount(String text) {
        return getAmountOfCharacters(text) / (getAmountOfWords(text) / 100.0);
    }

    // The average number of sentences per 100 words in the text.
    public static double getAverageSentenceCount(String text) {
        return getAmountOfSentences(text) / (getAmountOfWords(text) / 100.0);
    }

    public static int getAmountOfSyllables(String text) {
        String[] tab = text.split(REGEX_WORDS);
        int counter = 0;
        for (String t : tab) {
            counter += countSyllables(t);
        }
        return counter;
    }

    private static int countSyllables(String word) {
        int counter = 0;
        boolean isConsonant = true;
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));
            if (isConsonant) {
                if (VOWELS.indexOf(c) >= 0) {
                    counter++;
                    isConsonant = false;
                }
            } else {
                if (CONSONANTS.indexOf(c) >= 0 || c == SPECIAL_CASE_VOWEL) {
                    isConsonant = true;
                }
            }
        }
        char lastCharacter = word.charAt(word.length() - 1);
        counter = checkEndOfWord(counter, lastCharacter);
        return (counter <= 0) ? 1 : counter;
    }

    private static int checkEndOfWord(int counter, char lastCharacter) {
        return (lastCharacter == 'e') ? --counter : counter;
    }

    public static int getPolySyllables(String text) {
        String[] tab = text.split(REGEX_WORDS);
        final int minSyllablesInWord = 3;
        int counter = 0;
        for (String t : tab) {
            if (countSyllables(t) >= minSyllablesInWord) {
                counter++;
            }
        }
        return counter;
    }

    public static double getAverageReadingLevel(String text) {
        double sum = 0;
        String[] keys = TestType.getSearchKeys();
        for (String key : keys) {
            sum += TestFactory.getTest(key).getGradeLevel(text);
        }
        return (sum / keys.length);
    }
}
