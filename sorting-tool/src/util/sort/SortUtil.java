package util.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SortUtil {

    private SortUtil() {}

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

    // Sorts a List-object based on the number of key-occurrences.
    public static <K, V> void countSortList(List<K> listToBeSorted, Map<K, V> map, List<K> sortedKeysFromMap,
                                            List<V> sortedValuesFromMap) {
        CountSort.sort(listToBeSorted, map, sortedKeysFromMap, sortedValuesFromMap);
    }
}
