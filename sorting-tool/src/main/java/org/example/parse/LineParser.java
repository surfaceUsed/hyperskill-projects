package org.example.parse;

import org.example.util.sort.SortUtil;
import org.example.util.statistics.StatisticsUtil;

import java.util.*;

public class LineParser implements DataParser {

    private final List<String> lineList; // List that contains lines of text sorted in lexicographically order (ascending).
    private final Map<String, Integer> mapLineByOccurrence; // key = String input (sentence), value = Number of line (sentence) occurrences in input.
    private final List<String> sortedLinesByOccurrence; // List that contains lines sorted by their occurrence in the input, then sorted lexicographically.

    private final List<String> data;

    LineParser(List<String> data) {
        this.data = data;
        this.mapLineByOccurrence = new HashMap<>();
        this.lineList = new ArrayList<>();
        this.sortedLinesByOccurrence = new ArrayList<>();
        initDataStructures();
    }

    private void initDataStructures() {
        for (String input : this.data) {
            initLineList(input);
            initMapByOccurrence(input);
        }

        // TODO: if input is empty, end program here.
        SortUtil.quickSortList(this.lineList);
        initSortedByCount();
    }

    private void initLineList(String line) {
        this.lineList.add(line);
    }

    private void initMapByOccurrence(String line) {
        int currentValue = this.mapLineByOccurrence.getOrDefault(line, 0);
        this.mapLineByOccurrence.put(line, currentValue + 1);
    }

    private void initSortedByCount() {
        List<String> keysAsSortedList = SortUtil.quickSortSetToList(this.mapLineByOccurrence.keySet());
        List<Integer> valuesAsSortedList = SortUtil.quickSortSetToList(new HashSet<>(this.mapLineByOccurrence.values())); // Gets rid of duplicates.
        SortUtil.countSortList(this.sortedLinesByOccurrence, this.mapLineByOccurrence, keysAsSortedList, valuesAsSortedList);
    }

    private String getNaturalSortedLine() {
        return StatisticsUtil.getNaturalOrderStatistics(this.lineList, true);
    }

    private String getSortedLineByCount() {
        return StatisticsUtil.getCountStatistics(this.sortedLinesByOccurrence, this.mapLineByOccurrence);
    }

    private String formatNaturalOrder() {
        return String.format("""
                Total lines: %d
                Sorted data:
                %s""",
                this.lineList.size(), getNaturalSortedLine());
    }

    private String formatCountOrder() {
        return String.format("""
                Total lines: %d.
                %s""",
                this.lineList.size(), getSortedLineByCount());
    }

    // TODO: better validation of input. Will always return "formatCountOrder()" if sortType is not "natural".
    @Override
    public String parseData(String sortType) {
        return (sortType.equals("natural")) ? formatNaturalOrder() : formatCountOrder();
    }
}