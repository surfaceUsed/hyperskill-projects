package org.example.util;

public final class KMP {

    private KMP() {}

    /**
     *
     * Time complexity: O(n + m).
     *
     * KMP algorithm.
     *
     * Returns the index of the start of a matching pattern. If no match is found, return -1.
     */
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

    /**
     *
     * Time complexity: O(m).
     *
     * Takes a char-array of a specific pattern (can be "%PFD-", "pmview" etc), and returns an array that
     * represents the lengths and positions of the longest prefixes that are also suffices for the "patternArray".
     */
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