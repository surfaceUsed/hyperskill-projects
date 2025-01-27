package org.example.search;

import org.example.util.StringUtil;

import java.util.List;

public class BinarySearch {

    public static int getBinarySearchMatches(List<String> contactList, List<String> searchList) {
        int numberOfMatches = 0;
        int contactListLength = contactList.size() - 1;

        for (String searchName : searchList) {

            int start = 0;
            int end = contactListLength;

            while (start <= end) {

                int midIndex = (start + end) / 2;

                String name = StringUtil.getContactNameInfo(contactList.get(midIndex));

                if (name.equals(searchName)) {
                    numberOfMatches++;
                    break;
                }
                if (name.compareTo(searchName) > 0) {
                    end = midIndex - 1;
                }
                if (name.compareTo(searchName) < 0) {
                    start = midIndex + 1;
                }
            }
        }
        return numberOfMatches;
    }
}
