package handler;

import util.TimeUtil;
import util.search.SearchUtil;
import java.util.ArrayList;
import java.util.List;

public class HandleLinearSearch implements SearchHandler {

    private final List<String> contactList;
    private final List<String> searchNames;

    private long totalTimeTaken;
    private int numberOfMatches;

    HandleLinearSearch(List<String> contactList, List<String> searchNames) {
        this.contactList = new ArrayList<>(contactList);
        this.searchNames = searchNames;
        initSortAndSearchProcess();
    }

    private void initSortAndSearchProcess() {
        long start = TimeUtil.timer();
        this.numberOfMatches = SearchUtil.getLinearSearchMatches(this.contactList, this.searchNames);
        long end = TimeUtil.timer();
        this.totalTimeTaken = TimeUtil.calculateTotalRunTime(start, end);
    }

    @Override
    public void printResult() {
        System.out.println("Start searching (linear search)...");
        System.out.println("Found " + this.numberOfMatches + " / " + this.searchNames.size() + " entries. Time taken: " +
                TimeUtil.runTimeFormatter(this.totalTimeTaken));
    }
}
