package parse;

import util.sort.SortUtil;
import util.statistics.StatisticsUtil;
import java.util.*;

public class LongParser implements DataParser {

    private final List<Long> numberList; // List that contains numbers (long) in a sorted order (ascending).
    private final Map<Long, Integer> mapNumberOfOccurrences; // key = Long input (number), value = Counts the occurrences of a specific number from the input.
    private final List<Long> sortedListByOccurrences; // List that contains numbers sorted by their occurrence in the input, then sorted numerically in ascending order.

    private final List<String> data;
    LongParser(List<String> data) {
        this.data = data;
        this.numberList = new ArrayList<>();
        this.mapNumberOfOccurrences = new HashMap<>();
        this.sortedListByOccurrences = new ArrayList<>();
        initDataStructures();
    }

    private void initDataStructures() {
        for (String input : this.data) {
            String[] splitBySpace = input.split("\\s+");
            for (String splitText : splitBySpace) {
                try {
                    long number = Long.parseLong(splitText);
                    initList(number);
                    initMap(number);
                } catch (NumberFormatException e) {
                    System.out.println("\"" + splitText + "\" is not a long. It will be skipped.");
                }
            }
        }

        // TODO: If input is empty, end program here.
        SortUtil.quickSortList(this.numberList);
        initSortedListByCount();
    }

    private void initList(long number) {
        this.numberList.add(number);
    }

    private void initMap(long number) {
        int currentCount = this.mapNumberOfOccurrences.getOrDefault(number, 0);
        mapNumberOfOccurrences.put(number, currentCount + 1);
    }

    private void initSortedListByCount() {
        List<Long> keysAsSortedList = SortUtil.quickSortSetToList(this.mapNumberOfOccurrences.keySet());
        List<Integer> valuesAsSortedList = SortUtil.quickSortSetToList(new HashSet<>(this.mapNumberOfOccurrences.values())); // gets rid of dupliates.
        SortUtil.countSortList(this.sortedListByOccurrences, this.mapNumberOfOccurrences, keysAsSortedList, valuesAsSortedList);
    }

    private String getNaturalSortedNumbers() {
        return StatisticsUtil.getNaturalOrderStatistics(this.numberList, false);
    }

    private String getSortedNumbersByCount() {
        return StatisticsUtil.getCountStatistics(this.sortedListByOccurrences, this.mapNumberOfOccurrences);
    }

    private String formatNaturalOrder() {
        return String.format("""
                Total numbers: %d.
                Sorted data: %s""",
                this.numberList.size(), getNaturalSortedNumbers());
    }

    private String formatCountOrder() {
        return String.format("""
                Total numbers: %d.
                %s""",
                this.numberList.size(), getSortedNumbersByCount());
    }

    // TODO: better validation of input. Will always return "formatCountOrder()" if sortType is not "natural".
    @Override
    public String parseData(String sortType) {
        return (sortType.equals("natural")) ? formatNaturalOrder() : formatCountOrder();
    }
}