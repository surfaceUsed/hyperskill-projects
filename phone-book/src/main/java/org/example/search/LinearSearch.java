package org.example.search;

import java.util.List;

public class LinearSearch {

    public static int getLinearSearchMatches(List<String> contactList, List<String> searchList) {
        int numberOfMatches = 0;
        for (String searchName : searchList) {
            for (String contactInfo : contactList) {
                if (contactInfo.contains(searchName)) {
                    numberOfMatches++;
                    break;
                }
            }
        }
        return numberOfMatches;
    }
}
