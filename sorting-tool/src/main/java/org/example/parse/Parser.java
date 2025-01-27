package org.example.parse;

import java.util.List;

public class Parser {

    private static final String LONG = "long";
    private static final String LINE = "line";
    private static final String WORD = "word";

    private Parser() {}

    public static String parseData(String sortType, String dataType, List<String> data) {

        return switch (dataType) {
            case LONG -> new LongParser(data).parseData(sortType);
            case LINE -> new LineParser(data).parseData(sortType);
            case WORD -> new WordParser(data).parseData(sortType);
            default -> throw new IllegalStateException("Invalid data type: " + dataType);
        };
    }
}