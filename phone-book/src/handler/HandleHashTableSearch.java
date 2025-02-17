package handler;

import util.StringUtil;
import util.TimeUtil;
import util.search.SearchUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleHashTableSearch implements SearchHandler {

    private final List<String> contactList;
    private final List<String> searchNames;

    private long totalTimeTaken;
    private int numberOfMatches;
    private long searchTime;
    private long initTime;

    HandleHashTableSearch(List<String> contactList, List<String> searchNames) {
        this.contactList = new ArrayList<>(contactList);
        this.searchNames = searchNames;
        initSortAndSearchProcess();
    }

    private void initSortAndSearchProcess() {
        long start = TimeUtil.timer();
        Map<String, String> contactMap = initContactMap(this.contactList);
        search(contactMap, this.searchNames);
        long end = TimeUtil.timer();
        this.totalTimeTaken = TimeUtil.calculateTotalRunTime(start, end);
    }

    private Map<String, String> initContactMap(List<String> contactList) {
        long start = TimeUtil.timer();
        Map<String, String> contactMap = getMap(contactList);
        long end = TimeUtil.timer();
        this.initTime = TimeUtil.calculateTotalRunTime(start, end);
        return contactMap;
    }

    private Map<String, String> getMap(List<String> contactList) {
        Map<String, String> contactInfoMap = new HashMap<>();
        for (String contactInfo : contactList) {
            String contactName = StringUtil.getContactNameInfo(contactInfo);
            String phoneNumber = StringUtil.getContactNumberInfo(contactInfo);
            contactInfoMap.put(contactName, phoneNumber);
        }
        return contactInfoMap;
    }

    private void search(Map<String, String> contactMap, List<String> searchNames) {
        long start = TimeUtil.timer();
        this.numberOfMatches = SearchUtil.getHashTableSearchMatches(contactMap, searchNames);
        long end = TimeUtil.timer();
        this.searchTime = TimeUtil.calculateTotalRunTime(start, end);
    }

    @Override
    public void printResult() {
        System.out.println("Start searching (hash table)...");
        System.out.println("Found " + this.numberOfMatches + " / " + this.searchNames.size() + " entries. Time taken: " +
                TimeUtil.runTimeFormatter(this.totalTimeTaken));
        System.out.println("Creating time: " + TimeUtil.runTimeFormatter(this.initTime));
        System.out.println("Searching time: " + TimeUtil.runTimeFormatter(this.searchTime));
    }

}
