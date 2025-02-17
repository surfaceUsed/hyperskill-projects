package handler;

import util.TimeUtil;
import util.search.SearchUtil;
import util.sort.SortUtil;
import java.util.ArrayList;
import java.util.List;

public class HandleJumpSearch implements SearchHandler {

    private final List<String> contactList;
    private final List<String> searchNames;

    private long totalTimeTaken;
    private int numberOfMatches;
    private long searchTime;
    private long sortTime;

    HandleJumpSearch(List<String> contactList, List<String> searchNames) {
        this.contactList = new ArrayList<>(contactList);
        this.searchNames = searchNames;
        initSortAndSearchProcess();
    }

    private void initSortAndSearchProcess() {
        long start = TimeUtil.timer();
        sort(this.contactList);
        search(this.contactList, this.searchNames);
        long end = TimeUtil.timer();
        this.totalTimeTaken = TimeUtil.calculateTotalRunTime(start, end);
    }

    private void sort(List<String> contactList) {
        long start = TimeUtil.timer();
        SortUtil.bubbleSort(contactList);
        long end = TimeUtil.timer();
        this.sortTime = TimeUtil.calculateTotalRunTime(start, end);
    }

    private void search(List<String> sortedList, List<String> searchNames) {
        long start = TimeUtil.timer();
        this.numberOfMatches = SearchUtil.getJumpSearchMatches(sortedList, searchNames);
        long end = TimeUtil.timer();
        this.searchTime = TimeUtil.calculateTotalRunTime(start, end);
    }

    @Override
    public void printResult() {
        System.out.println("Start searching (bubble sort + jump search)...");
        System.out.println("Found " + this.numberOfMatches + " / " + this.searchNames.size() + " entries. Time taken: " +
                TimeUtil.runTimeFormatter(this.totalTimeTaken));
        System.out.println("Sorting time: " + TimeUtil.runTimeFormatter(this.sortTime));
        System.out.println("Searching time: " + TimeUtil.runTimeFormatter(this.searchTime));
    }
}
