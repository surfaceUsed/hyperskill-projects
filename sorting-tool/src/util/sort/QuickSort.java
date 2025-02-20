package util.sort;

import java.util.List;

/**
 *
 * Sorts the given list using the QuickSort algorithm.
 * The elements in the list **must** implement the Comparable interface,
 * as comparisons are performed using the compareTo() method.
 */
final class QuickSort {

    private QuickSort() {}

    // The element "T" has to implement the "Comparable" interface.
    static <T extends Comparable<T>> void quickSort(List<T> list) {
        sort(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<T>> void sort(List<T> list, int start, int end) {

        if (start >= end) {
            return;
        }

        T pivotValue = list.get(end);

        int breakPoint = partition(list, start, end, pivotValue);

        sort(list, start, breakPoint - 1);
        sort(list, breakPoint + 1, end);
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int start, int end, T pivotValue) {
        int i = start, j = end - 1;

        // Handles edge cases.
        while (i <= j) {

            while ((i <= j) && list.get(i).compareTo(pivotValue) <= 0) {
                i++;
            }

            while ((i <= j) && list.get(j).compareTo(pivotValue) >= 0) {
                j--;
            }

            if (i < j) {
                swap(list, i, j);
                // Increment and decrement values so that we can move on and compare the next elements in the list.
                i++;
                j--;
            }

        }
        swap(list, i, end);
        return i; // Returns the index of where the left- and right values are separated (and the pivot value is placed).
    }

    private static <T extends Comparable<T>> void swap(List<T> list, int firstIndex, int lastIndex) {
        T temp = list.get(firstIndex);
        list.set(firstIndex, list.get(lastIndex));
        list.set(lastIndex, temp);
    }
}