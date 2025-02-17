package util.search;

import java.util.List;

class LinearSearch {

    static int linearSearchMatch(List<String> contacts, List<String> searchList) {
        int numberOfMatches = 0;
        for (String searchName : searchList) {
            for (String contactInfo : contacts) {
                if (contactInfo.contains(searchName)) {
                    numberOfMatches++;
                    break;
                }
            }
        }
        return numberOfMatches;
    }
}
