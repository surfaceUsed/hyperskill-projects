package util.search;

import java.util.List;
import java.util.Map;

class HashTableSearch {

    static int getHashTableMatches(Map<String, String> contactMap, List<String> searchList) {
        int numberOfmatches = 0;
        for (String contactName : searchList) {
            if (contactMap.containsKey(contactName)) {
                numberOfmatches++;
            }
        }
        return numberOfmatches;
    }
}
