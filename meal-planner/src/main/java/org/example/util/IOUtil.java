package org.example.util;

import java.util.Scanner;

public class IOUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String writeInput() {
        return SCANNER.nextLine();
    }

    public static void closeInput() {
        SCANNER.close();
    }
}
