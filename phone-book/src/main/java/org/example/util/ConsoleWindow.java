package org.example.util;

public class ConsoleWindow {

    private ConsoleWindow() {}

    public static void startLinearSearch() {
        System.out.println("Start searching (linear search)...");
    }

    public static void starJumpSearch() {
        System.out.println("Start searching (bubble sort + jump search)...");
    }

    public static void startBinarySearch() {
        System.out.println("Start searching (quick sort + binary search)...");
    }

    public static void startHashTableSearch() {
        System.out.println("Start searching (hash table)...");
    }

    public static void printResult(int matchesFound, int numberOfPotentialMatches, long timeTaken) {
        System.out.println("Found " + matchesFound + " / " + numberOfPotentialMatches + " entries. Time taken: " +
                TimeUtil.runTimeFormatter(timeTaken));
    }

    public static void printSearchRunTime(long runTime) {
        System.out.println("Searching time: " + TimeUtil.runTimeFormatter(runTime));
    }

    public static void printSortRunTime(long runTime) {
        System.out.println("Sorting time: " + TimeUtil.runTimeFormatter(runTime));
    }

    public static void printInitRunTime(long runTime) {
        System.out.println("Creating time: " + TimeUtil.runTimeFormatter(runTime));
    }
}
