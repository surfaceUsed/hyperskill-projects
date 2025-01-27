package org.example.controller;

import org.example.sort.BubbleSort;
import org.example.sort.QuickSort;
import org.example.util.TimeUtil;
import java.util.ArrayList;
import java.util.List;

class SortHandler {

    private long bubbleSortRunTime;
    private long quickSortRunTime;

    SortHandler() {}

    List<String> runBubbleSort(List<String> contactList) {
        long start = TimeUtil.timer();
        List<String> sortedContactList = new ArrayList<>(contactList);
        BubbleSort.bubbleSort(sortedContactList);
        long end = TimeUtil.timer();
        this.bubbleSortRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return sortedContactList;
    }

    List<String> runQuickSort(List<String> contactList) {
        long start = TimeUtil.timer();
        List<String> sortedContactList = new ArrayList<>(contactList);
        QuickSort.quickSort(sortedContactList);
        long end = TimeUtil.timer();
        this.quickSortRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return sortedContactList;
    }

    long getBubbleSortRunTime() {
        return bubbleSortRunTime;
    }

    long getQuickSortRunTime() {
        return quickSortRunTime;
    }
}
