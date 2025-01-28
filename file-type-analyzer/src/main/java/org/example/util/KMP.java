package org.example.util;

public final class KMP {

    private KMP() {}

    public static int kmp(String text, String pattern) {

        int i = 0, j = 0, noMatch = -1;

        if (text.isEmpty()) {
            return noMatch;
        }
        if (text.length() == pattern.length()) {
            return (text.equals(pattern)) ? 0 : noMatch;
        }
        if (pattern.isEmpty()) {
            return 0;
        }

        char[] textArr = text.toLowerCase().toCharArray();
        char[] patArr = pattern.toLowerCase().toCharArray();
        int[] lps = longestPrefixSuffix(patArr);

        while (i < textArr.length) {

            if (textArr[i] == patArr[j]) {
                i++;
                j++;
                if (j == patArr.length) {
                    return (i - j);
                }

            } else {

                if (j != 0) {
                    j = lps[j - 1];

                } else {
                    i++;
                }
            }
        }
        return noMatch;
    }

    private static int[] longestPrefixSuffix(char[] patternArray) {

        int[] lps = new int[patternArray.length];

        int i = 1, j = 0;

        while (i < patternArray.length) {

            if (patternArray[i] == patternArray[j]) {
                lps[i] = ++j;
                i++;

            } else {

                if (j != 0) {
                    j = lps[j - 1];

                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}