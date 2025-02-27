package util;

import contact.Contact;
import java.util.ArrayList;
import java.util.List;

public final class PatternMatcher {

    private PatternMatcher() {}

    public static List<String> getSearchMatches(List<Contact> contacts, String query) {
        List<String> matches = new ArrayList<>();

        if (contacts.isEmpty() || query.isEmpty() || query.isBlank()) {
            return matches;
        }

        char[] queryTab = query.toLowerCase().toCharArray();
        int[] prePost = prefixPostfix(queryTab);

        for (Contact contact : contacts) {
            if (isMatch(contact.getContactInfo().toLowerCase(), queryTab, prePost)) {
                matches.add(contact.getFullName());
            }
        }
        return matches;
    }

    /**
     *
     * Knut-morris-prat algorithm for matching a pattern in a text.
     * Returns true if a matching pattern is found.
     */
    private static boolean isMatch(String word, char[] query, int[] prePost) {

        if (word.length() < query.length || word.isEmpty()) {
            return false;
        }
        if (word.length() == query.length) {
            return word.equals(String.valueOf(query));
        }

        char[] wordTab = word.toLowerCase().toCharArray();
        int i = 0, j = 0;

        while (i < wordTab.length) {
            if (wordTab[i] == query[j]) {
                i++;
                j++;
                if (j == query.length) {
                    return true;
                }
            } else if (wordTab[i] != query[j]) {
                if (j > 0) {
                    j = prePost[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    private static int[] prefixPostfix(char[] query) {

        int[] prePost = new int[query.length];
        int i = 1, j = 0;

        while (i != query.length) {
            if (query[i] == query[j]) {
                prePost[i] = ++j;
                i++;
            } else if (query[i] != query[j]) {
                if (j > 0) {
                    j = prePost[j - 1];
                } else {
                    i++;
                }
            }
        }
        return prePost;
    }
}