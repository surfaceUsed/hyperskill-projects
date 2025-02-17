package util.sort;

import java.util.List;

public class SortUtil {

    private SortUtil() {}

    public static void quickSort(List<String> contactList) {
        QuickSort.quickSort(contactList);
    }

    public static void bubbleSort(List<String> contactList) {
        BubbleSort.bubbleSort(contactList);
    }
}
