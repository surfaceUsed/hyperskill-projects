package org.example.util.sort;

import java.util.List;
import java.util.Map;

final class CountSort {

    private CountSort() {}

    static <K, V> void sort(List<K> listToBeSortedByCount, Map<K,V> map, List<K> sortedKeysFromMap,
                            List<V> sortedValuesFromMap) {
        for (V value : sortedValuesFromMap) {
            for (K key : sortedKeysFromMap) {
                if (map.get(key).equals(value)) {
                    listToBeSortedByCount.add(key);
                }
            }
        }
    }
}
