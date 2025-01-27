package org.example.sort;

import org.example.util.StringUtil;

import java.util.List;

public class QuickSort {

    private QuickSort() {}

    public static void quickSort(List<String> contactList) {
        sort(contactList, 0, contactList.size() - 1);
    }

    private static void sort(List<String> listToSort, int firstIndex, int lastIndex) {

        if (firstIndex >= lastIndex) {
            return;
        }

        int breakingPoint = partition(listToSort, firstIndex, lastIndex);

        sort(listToSort, firstIndex, breakingPoint - 1);
        sort(listToSort, breakingPoint + 1, lastIndex);
    }

    private static int partition(List<String> listToSort, int start, int end) {

        int medianIndex = getMedian(listToSort, start, end);
        String pivotValue = StringUtil.getContactNameInfo(listToSort.get(medianIndex));

        swap(listToSort, medianIndex, end);

        int i = 0, j = end;

        while (i != j) {

            while ((i < j) && (StringUtil.getContactNameInfo(listToSort.get(i)).compareTo(pivotValue)) <= 0) {
                i++;
            }
            while ((i < j) && (StringUtil.getContactNameInfo(listToSort.get(j)).compareTo(pivotValue)) >= 0) {
                j--;
            }
            swap(listToSort, i, j);
        }
        swap(listToSort, i, end);
        return i;
    }

    private static int getMedian(List<String> list, int firstIndex, int lastIndex) {
        int midIndex = firstIndex + (lastIndex - firstIndex) / 2;
        return calculateMedianIndex(list, firstIndex, midIndex, lastIndex);
    }

    private static int calculateMedianIndex(List<String> list, int startIndex, int midIndex, int endIndex) {
        int potentialMedianIndex;
        int medianIndex;
        String firstElement = StringUtil.getContactNameInfo(list.get(startIndex));
        String midElement = StringUtil.getContactNameInfo(list.get(midIndex));
        String lastElement = StringUtil.getContactNameInfo(list.get(endIndex));

        if (firstElement.compareTo(midElement) > 0) {
            potentialMedianIndex = (firstElement.compareTo(lastElement) > 0) ? endIndex : startIndex;
            medianIndex = (list.get(potentialMedianIndex).compareTo(midElement) > 0) ?
                    potentialMedianIndex : midIndex;
        } else {
            potentialMedianIndex = (midElement.compareTo(lastElement) > 0) ? endIndex : midIndex;
            medianIndex = (list.get(potentialMedianIndex).compareTo(firstElement) > 0) ?
                    potentialMedianIndex : startIndex;
        }
        return medianIndex;
    }

    private static void swap(List<String> list, int firstIndex, int lastIndex) {
        String temp = list.get(firstIndex);
        list.set(firstIndex, list.get(lastIndex));
        list.set(lastIndex, temp);
    }
}
