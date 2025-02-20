package parse;

import util.sort.SortUtil;
import util.statistics.StatisticsUtil;
import java.util.*;

public class WordParser implements DataParser {

    private final List<String> wordList; // List that contains words (Strings) sorted in lexicographically order (ascending).
    private final Map<String, Integer> mapWordsByOccurrence; // key = String input (word), value = Number of word occurrences in input.
    private final List<String> sortedWordListByOccurrence; // List that contains words sorted by their occurrence in the input, then sorted lexicographically.
    private final List<String> data;

    WordParser(List<String> data) {
        this.data = data;
        this.wordList = new ArrayList<>();
        this.mapWordsByOccurrence = new HashMap<>();
        this.sortedWordListByOccurrence = new ArrayList<>();
        initDataStructures();
    }

    public void initDataStructures() {
        for (String input : this.data) {
            String[] splitBySpace = input.split("\\s+");
            for (String word : splitBySpace) {
                initWordList(word);
                initMapWordByOccurrence(word);
            }
        }

        // TODO: If input is empty, end program here.
        SortUtil.quickSortList(this.wordList);
        initSortedListByCount();
    }

    private void initWordList(String word) {
        this.wordList.add(word);
    }

    private void initMapWordByOccurrence(String word) {
        int currentValue = this.mapWordsByOccurrence.getOrDefault(word, 0);
        this.mapWordsByOccurrence.put(word, currentValue + 1);
    }

    private void initSortedListByCount() {
        List<String> keysAsSortedList = SortUtil.quickSortSetToList(this.mapWordsByOccurrence.keySet());
        List<Integer> valuesAsSortedList = SortUtil.quickSortSetToList(new HashSet<>(this.mapWordsByOccurrence.values())); // Gets rid of duplicates.
        SortUtil.countSortList(this.sortedWordListByOccurrence, this.mapWordsByOccurrence, keysAsSortedList, valuesAsSortedList);
    }

    private String getNaturalSortedWords() {
        return StatisticsUtil.getNaturalOrderStatistics(this.wordList, false);
    }

    private String getSortedWordsByCount() {
        return StatisticsUtil.getCountStatistics(this.sortedWordListByOccurrence, this.mapWordsByOccurrence);
    }

    private String formatNaturalOrder() {
        return String.format("""
                Total words: %d.
                Sorted data: %s""",
                this.wordList.size(), getNaturalSortedWords());
    }

    private String formatCountOrder() {
        return String.format("""
                Total words: %d.
                %s""",this.wordList.size(), getSortedWordsByCount());
    }

    // TODO: better validation of input. Will always return "formatCountOrder()" if sortType is not "natural".
    @Override
    public String parseData(String sortType) {
        return (sortType.equals("natural"))? formatNaturalOrder() : formatCountOrder();
    }
}