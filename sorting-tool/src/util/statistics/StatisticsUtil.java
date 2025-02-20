package util.statistics;

import java.util.List;
import java.util.Map;

public class StatisticsUtil {

    private StatisticsUtil() {}

    public static <T> String getNaturalOrderStatistics(List<T> list, boolean isLine) {
        StringBuilder sb = new StringBuilder();
        for (T element : list) {
            if (isLine) {
                sb.append(element).append("\n");
            } else {
                sb.append(element).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static <T> String getCountStatistics(List<T> list, Map<T, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (T element : list) {
            sb.append(element)
                    .append(": ")
                    .append(map.get(element))
                    .append(" time(s), ")
                    .append((int) getPercentage(map.get(element), list.size()))
                    .append("%")
                    .append("\n");
        }
        return sb.toString().trim();
    }

    private static double getPercentage(int numberOfOccurrences, int totalElements) {
        return ((double) numberOfOccurrences / totalElements) * 100;
    }
}
