package util.search;

import java.util.List;
import java.util.Map;

public class SearchUtil {

    private SearchUtil() {}

    public static int getBinarySearchMatches(List<String> contactList, List<String> searchNames) {
        return BinarySearch.getBinarySearchMatches(contactList, searchNames);
    }

    public static int getHashTableSearchMatches(Map<String, String> contactMap, List<String> searchNames) {
        return HashTableSearch.getHashTableMatches(contactMap, searchNames);
    }

    public static int getJumpSearchMatches(List<String> contactList, List<String> searchNames) {
        return JumpSearch.getJumpSearchMatches(contactList, searchNames);
    }

    public static int getLinearSearchMatches(List<String> contactList, List<String> searchNames) {
        return LinearSearch.linearSearchMatch(contactList, searchNames);
    }
}
