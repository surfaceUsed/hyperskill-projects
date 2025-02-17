package util;

public class TimeUtil {

    private TimeUtil() {}

    public static long timer() {
        return System.currentTimeMillis();
    }

    public static long calculateTotalRunTime(long start, long end) {
        return Math.abs(start - end);
    }

    public static String runTimeFormatter(long runTime) {
        long min = (runTime / 1000) / 60;
        long sec = (runTime / 1000) % 60;
        long milli = runTime % 1000;
        return min + " min. " + sec + " sec. " + milli + " ms.";
    }
}
