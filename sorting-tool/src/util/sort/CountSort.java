package util.sort;

import java.util.List;
import java.util.Map;

final class CountSort {

    private CountSort() {}

    /**
     * Sorts keys (data types such as words, lines, or numbers) based on their frequency of occurrence.
     * Iterates through sortedValuesFromMap, which contains the sorted counts of occurrences,
     * and finds the corresponding keys in keysFromMap using map.get(key).
     * When a match is found, the key (word, line, or number) is added to listToBeSortedByCount in
     * the order dictated by sortedValuesFromMap.
     *
     * @param listToBeSortedByCount The output list where sorted keys (words, lines, or numbers) will be stored.
     * @param map The map containing key-value pairs, where keys represent data types (e.g., words, lines, numbers)
     *            and values represent their number of occurrences.
     * @param keysFromMap The list of keys (data types) from the map, used for lookup.
     * @param sortedValuesFromMap The list of occurrence counts, sorted in the desired order, determining the
     *                            sorting order of the keys.
     * @param <K> The type of keys in the map (e.g., String for words, numbers, or lines).
     * @param <V> The type of values in the map, representing the frequency of occurrences (e.g., Integer).
     */
    static <K, V> void sort(List<K> listToBeSortedByCount, Map<K, V> map, List<K> keysFromMap,
                            List<V> sortedValuesFromMap) {
        for (V value : sortedValuesFromMap) {
            for (K key : keysFromMap) {
                if (map.get(key).equals(value)) {
                    listToBeSortedByCount.add(key);
                }
            }
        }
    }
}
