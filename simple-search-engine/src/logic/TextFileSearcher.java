package logic;

import config.ConfigurationManager;
import enums.SearchStrategies;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TextFileSearcher {

    private static final ConfigurationManager CONFIGURATIONS = ConfigurationManager.getInstance();

    private final List<String> lines = CONFIGURATIONS.getFileLinesAsList();
    private final Map<String, List<Integer>> invertedIndex = CONFIGURATIONS.getInvertedIndex();

    public void queryIndex(String strategy, String query) {
        String[] search = query.toLowerCase().split("\\s+"); // Splits each word in the query.

        if (search.length != 0) { // As long as the search is not empty.

            Map<Integer, Integer> indexWordCounter = getIndexWordCounter(search); // Maps line number with the count of total word matches of the search query.

            if (!indexWordCounter.isEmpty()) {

                SearchStrategies strategyType = SearchStrategies.getStrategy(strategy);

                switch (strategyType) {

                    case ALL -> searchAll(indexWordCounter, search.length);

                    case ANY -> searchAny(indexWordCounter.keySet());

                    case NONE -> searchNone(indexWordCounter.keySet());

                    case INVALID -> System.out.println("Invalid strategy type.");
                }
            } else {
                System.out.println("No matches found!");
            }
        } else {
            System.out.println("Query is empty.");
        }
    }

    private void searchAll(Map<Integer, Integer> indexMap, int wordsInSearch) {
        for (Integer numb : indexMap.keySet()) {
            // If all the words on a specific sentence index matches all the words in the search
            // then we print that line.
            if (indexMap.get(numb) == wordsInSearch) {
                System.out.println(this.lines.get(numb));
            }
        }
    }

    private void searchAny(Set<Integer> keys) {
        for (Integer index : keys) {
            System.out.println(this.lines.get(index));
        }
    }

    private void searchNone(Set<Integer> keys) {
        for (int i = 0; i < this.lines.size(); i++) {
            if (!isMatchingIndex(keys, i)) {
                System.out.println(this.lines.get(i));
            }
        }
    }

    private boolean isMatchingIndex(Set<Integer> setNumbers, int index) {
        for (Integer numb : setNumbers) {
            if (numb == index) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a map that associates each line number with the count of query words found in that line.
     *
     * For each word in the query, the method checks if it exists in the inverted index map.
     * It then updates the count of how many query words appear in each line where the word is found.
     * The resulting map helps determine which lines match the search criteria based on the number of
     * query words found in each line.
     *
     * @param query An array of words to search for in the text.
     * @return A map where the key is the line number, and the value is the count of query words found in that line.
     */
    private Map<Integer, Integer> getIndexWordCounter(String[] query) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (String word : query) {
            if (this.invertedIndex.containsKey(word)) {
                List<Integer> indexList = this.invertedIndex.get(word);
                for (Integer index : indexList) {
                    indexMap.putIfAbsent(index, 0);
                    if (indexMap.containsKey(index)) {
                        int value = indexMap.get(index);
                        indexMap.put(index, ++value);
                    }
                }
            }
        }
        return indexMap;
    }

    public void printList() {
        this.lines.forEach(System.out::println);
        System.out.println();
    }
}
