package org.example.search;

import java.util.List;
import java.util.Map;

public class HashTableSearch {

    public static int getHashTableMatches(Map<String, String> contactmap, List<String> searchList) {
        int numberOfmatches = 0;
        for (String contactName : searchList) {
            if (contactmap.containsKey(contactName)) {
                numberOfmatches++;
            }
        }
        return numberOfmatches;
    }
}
