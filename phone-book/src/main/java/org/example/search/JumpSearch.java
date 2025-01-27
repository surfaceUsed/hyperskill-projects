package org.example.search;

import org.example.util.StringUtil;

import java.util.List;

public class JumpSearch {

    public static int getJumpSearchMatches(List<String> sortedList, List<String> searchList) {

        int numberOfMatches = 0;
        int length = sortedList.size();
        int jumpValue = sqrt(length);
        for (String contact : searchList) {
            if (jumpSearch(sortedList, jumpValue, length, contact)) {
                numberOfMatches++;
            }
        }
        return numberOfMatches;
    }

    private static boolean jumpSearch(List<String> contactList, int jumpValue, int length, String key) {
        int start = 0, end = jumpValue;
        while ((end < length) && (StringUtil.getContactNameInfo(contactList.get(end)).compareTo(key)) <= 0) {
            start = end;
            end += jumpValue;
        }
        for (int i = start; i < Math.min(end, length); i++) {
            if (StringUtil.getContactNameInfo(contactList.get(i)).equals(key)) {
                return true;
            }
        }
        return false;
    }

    private static int sqrt(int number) {
        return (int) Math.sqrt(number);
    }
}
