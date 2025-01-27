package org.example.controller;

import org.example.search.BinarySearch;
import org.example.search.HashTableSearch;
import org.example.search.JumpSearch;
import org.example.search.LinearSearch;
import org.example.util.TimeUtil;
import java.util.List;
import java.util.Map;

class SearchHandler {

    private long linearSearchRunTime;
    private long jumpSearchRunTime;
    private long binarySearchRunTime;
    private long hashTableSearchRunTime;

    SearchHandler() {}

    int runLinearSearch(List<String> contactList, List<String> searchList) {
        long start = TimeUtil.timer();
        int numberOfMatches = LinearSearch.getLinearSearchMatches(contactList, searchList);
        long end = TimeUtil.timer();
        this.linearSearchRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return numberOfMatches;
    }

    int runJumpSearch(List<String> sortedContactList, List<String> searchList) {
        long start = TimeUtil.timer();
        int numberOfMatches = JumpSearch.getJumpSearchMatches(sortedContactList, searchList);
        long end = TimeUtil.timer();
        this.jumpSearchRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return numberOfMatches;
    }

    int runBinarySearch(List<String> sortedContactList, List<String> searchList) {
        long start = TimeUtil.timer();
        int numberOfMatches = BinarySearch.getBinarySearchMatches(sortedContactList, searchList);
        long end = TimeUtil.timer();
        this.binarySearchRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return numberOfMatches;
    }

    int runHashTableSearch(Map<String, String> contactMap, List<String> searchList) {
        long start = TimeUtil.timer();
        int numberOfMatches = HashTableSearch.getHashTableMatches(contactMap, searchList);
        long end = TimeUtil.timer();
        this.hashTableSearchRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return numberOfMatches;
    }

    long getLinearSearchRunTime() {
        return linearSearchRunTime;
    }

    long getJumpSearchRunTime() {
        return jumpSearchRunTime;
    }

    long getBinarySearchRunTime() {
        return binarySearchRunTime;
    }

    long getHashTableSearchRunTime() {
        return hashTableSearchRunTime;
    }
}
