package org.example.parse;

import org.example.error.InvalidTypeException;

import java.util.HashMap;
import java.util.Map;

public class CMLParser {

    private static final String DEFAULT_SORTING_TYPE = "natural";
    private static final String DEFAULT_DATA_TYPE = "word";
    private static final String SORTING_TYPE = "-sortingType";
    private static final String DATA_TYPE = "-dataType";
    private static final String INPUT = "-inputFile";
    private static final String OUTPUT = "-outputFile";

    private String sortingType;
    private String dataType;
    private String inputFilePath;
    private String outputFilePath;
    private boolean terminateProgram = false;

    public CMLParser(String[] args) {
        buildCommandLineArgument(args);
    }

    public String getSortingType() {
        return (this.sortingType != null && !this.sortingType.isBlank()) ?
                this.sortingType : DEFAULT_SORTING_TYPE;
    }

    private void setSortingType(String sortingType) {
        this.sortingType = sortingType;
    }

    public static String getDefaultDataType() {
        return DEFAULT_DATA_TYPE;
    }

    public String getDataType() {
        return (this.dataType != null && !dataType.isBlank()) ?
                this.dataType : DEFAULT_DATA_TYPE;
    }

    private void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInputPath() {
        return this.inputFilePath;
    }

    private void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputPath() {
        return this.outputFilePath;
    }

    private void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public boolean readFromFile() {
        return (this.inputFilePath != null);
    }

    public boolean writeToFile() {
        return (this.outputFilePath != null);
    }

    public boolean terminateProgram() {
        return this.terminateProgram;
    }

    private void buildCommandLineArgument(String[] args) {

        for (Map.Entry<String, String> entrySet : mapInput(args).entrySet()) {

            try {

                switch (entrySet.getKey()) {

                    case SORTING_TYPE:
                        if (!isValidDataType(entrySet.getValue())) {
                            throw new InvalidTypeException("No sorting type defined!");
                        } else {
                            this.setSortingType(entrySet.getValue());
                        }
                        break;

                    case DATA_TYPE:
                        if (!isValidDataType(entrySet.getValue())) {
                            throw new InvalidTypeException("No data type defined!");
                        } else {
                            this.setDataType(entrySet.getValue());
                        }
                        break;

                    case INPUT:
                        this.setInputFilePath(entrySet.getValue());
                        break;

                    case OUTPUT:
                        this.setOutputFilePath(entrySet.getValue());
                        break;

                    default:
                        System.out.println("\"" + entrySet.getKey() + "\" is not a valid parameter. It will be skipped.");
                        break;
                }

            } catch (InvalidTypeException e) {
                System.out.println(e.getMessage());
                this.terminateProgram = true;
            }
        }
    }

    private Map<String, String> mapInput(String[] args) {
        Map<String, String> inputMap = new HashMap<>();
        int index = 0;
        while (index < args.length - 1) {
            inputMap.put(args[index], args[++index]);
            index++;
        }
        return inputMap;
    }

    private boolean isValidDataType(String type) {
        return (type != null && !type.isBlank());
    }
}