package org.example.controller;

import org.example.util.ConsoleWindow;

import java.util.List;
import java.util.Map;

public class Controller {

    private final InitHandler INITIALIZER;
    private final SearchHandler SEARCH;
    private final SortHandler SORT;

    public Controller() {
        this.INITIALIZER = new InitHandler();
        this.SEARCH = new SearchHandler();
        this.SORT = new SortHandler();
    }

    public void run(List<String> contactList, List<String> searchList) {
        runLinearSearch(contactList, searchList);
        System.out.println();
        runBubbleSortJumpSearch(contactList, searchList);
        System.out.println();
        runQuickSortBinarySearch(contactList, searchList);
        System.out.println();
        runInitHashTableSearch(contactList, searchList);
    }

    void runLinearSearch(List<String> contactList, List<String> searchList) {
        ConsoleWindow.startLinearSearch();
        int numberOfMatches = this.SEARCH.runLinearSearch(contactList, searchList);
        ConsoleWindow.printResult(numberOfMatches, searchList.size(), this.SEARCH.getLinearSearchRunTime());
    }

    void runBubbleSortJumpSearch(List<String> contactList, List<String> searchList) {
        ConsoleWindow.starJumpSearch();
        List<String> sortedContactList = this.SORT.runBubbleSort(contactList);
        int numberOfMatches = this.SEARCH.runJumpSearch(sortedContactList, searchList);
        long sortRunTime = this.SORT.getBubbleSortRunTime();
        long searchRunTime = this.SEARCH.getJumpSearchRunTime();
        ConsoleWindow.printResult(numberOfMatches, searchList.size(), (sortRunTime + searchRunTime));
        ConsoleWindow.printSortRunTime(sortRunTime);
        ConsoleWindow.printSearchRunTime(searchRunTime);
    }

    void runQuickSortBinarySearch(List<String> contactList, List<String> searchList) {
        ConsoleWindow.startBinarySearch();
        List<String> sortedContactList = this.SORT.runQuickSort(contactList);
        int numberOfMatches = this.SEARCH.runBinarySearch(sortedContactList, searchList);
        long sortRunTime = this.SORT.getQuickSortRunTime();
        long searchRunTime = this.SEARCH.getBinarySearchRunTime();
        ConsoleWindow.printResult(numberOfMatches, searchList.size(), (sortRunTime + searchRunTime));
        ConsoleWindow.printSortRunTime(sortRunTime);
        ConsoleWindow.printSearchRunTime(searchRunTime);
    }

    void runInitHashTableSearch(List<String> contactList, List<String> searchList) {
        ConsoleWindow.startHashTableSearch();
        Map<String, String> contactMap = this.INITIALIZER.runHashTableInitializer(contactList);
        int numberOfMatches = this.SEARCH.runHashTableSearch(contactMap, searchList);
        long initRunTime = this.INITIALIZER.getInitRunTime();
        long searchRunTime = this.SEARCH.getHashTableSearchRunTime();
        ConsoleWindow.printResult(numberOfMatches, searchList.size(), (initRunTime + searchRunTime));
        ConsoleWindow.printInitRunTime(initRunTime);
        ConsoleWindow.printSearchRunTime(searchRunTime);
    }
}
