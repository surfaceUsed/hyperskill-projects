package game.util;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public final class RandomStringGenerator {

    public static final int MAX_AMOUNT_OF_DIGITS = 10; // 0-9

    private RandomStringGenerator() {}

    public static String createRandomString(int numberOfSymbols) {

        Set<String> finalSet = new LinkedHashSet<>();
        String numbersAndCharacters = createRandomNumbers(numberOfSymbols) + createRandomCharacters(numberOfSymbols);
        int size = 0, length = numbersAndCharacters.length();

        while (size != length) {
            finalSet.add(String.valueOf(numbersAndCharacters.charAt(new Random().nextInt(length))));
            size = finalSet.size();
        }

        return createString(finalSet);
    }

    private static String createRandomNumbers(int numberOfSymbols) {

        Set<String> numberSet = new LinkedHashSet<>();
        int size = 0, totalNumbers = getTotalNumbers(numberOfSymbols);

        while (size != totalNumbers) {
            numberSet.add(String.valueOf(new Random().nextInt(totalNumbers)));
            size = numberSet.size();
        }

        return createString(numberSet);
    }

    private static int getTotalNumbers(int numberOfSymbols) {
        return Math.min(numberOfSymbols, MAX_AMOUNT_OF_DIGITS);
    }

    private static String createRandomCharacters(int numberOfSymbols) {

        Set<String> characterSet = new LinkedHashSet<>();
        int size = 0;
        int dif = numberOfSymbols - MAX_AMOUNT_OF_DIGITS;

        if (dif > 0) {
            while (size != dif) {
                characterSet.add(String.valueOf(createRandomSybmbol(dif)));
                size = characterSet.size();
            }
        }

        return createString(characterSet);
    }

    private static char createRandomSybmbol(int dif) {
        return (char) (new Random().nextInt(dif) + 'a');
    }

    private static String createString(Set<String> set) {

        if (set != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : set) {
                sb.append(s);
            }
            return sb.toString();
        }

        return "";
    }
}

