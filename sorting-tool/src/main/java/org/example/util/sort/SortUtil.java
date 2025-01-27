package org.example.util.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SortUtil {

    private SortUtil() {}

    // Takes a List-object, and returns a new sorted List-object.
    // Method not in use.
    public static <T extends Comparable<T>> List<T> quickSortToNewList(List<T> listToSort) {
        List<T> sorted = new ArrayList<>(listToSort);
        QuickSort.quickSort(sorted);
        return sorted;
    }

    // Takes a Set-object, sorts it, and returns it as a List-object.
    public static <T extends Comparable<T>> List<T> quickSortSetToList(Set<T> set) {
        List<T> sortedSet = new ArrayList<>(set);
        QuickSort.quickSort(sortedSet);
        return sortedSet;
    }

    // Takes a List-object, and sorts it.
    public static <T extends Comparable<T>> void quickSortList(List<T> listToSort) {
        QuickSort.quickSort(listToSort);
    }

    // Takes a Map-object, and the key- and value-pairs from the Map-object separated in their own sorted
    // List-objects (containing no duplicates).
    // Returns a List-object that is sorted based on the number of key-occurrences.
    // Method not in use.
    public static <K, V> List<K> countSortToNewList(Map<K, V> map, List<K> sortedKeysFromMap,
                                                    List<V> sortedValuesFromMap) {
        List<K> listTobeSorted = new ArrayList<>();
        CountSort.sort(listTobeSorted, map, sortedKeysFromMap, sortedValuesFromMap);
        return listTobeSorted;
    }

    // Sorts a List-object based on the number of key-occurrences.
    public static <K, V> void countSortList(List<K> listToBeSorted, Map<K, V> map, List<K> sortedKeysFromMap,
                                            List<V> sortedValuesFromMap) {
        CountSort.sort(listToBeSorted, map, sortedKeysFromMap, sortedValuesFromMap);
    }
}
