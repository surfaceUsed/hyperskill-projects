package org.example.controller;

import org.example.cipher.TextManipulator;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    private static final String MODE = "-mode";
    private static final String KEY = "-key";
    private static final String IN = "-in";
    private static final String OUT = "-out";
    private static final String DATA = "-data";
    private static final String ALGORITHM = "-alg";

    private final Map<String, String> cipherMap;

    private String modeValue;
    private String keyValue;
    private String inValue;
    private String outValue;
    private String dataValue;
    private String algorithmValue;

    public Controller(String[] args) {
        this.cipherMap = initMapper(args);
        initValues();
    }

    // Maps input from command line to key-value pairs.
    private Map<String, String> initMapper(String[] args) {

        int index = 0;
        Map<String, String> map = new HashMap<>();

        while (index != args.length) {

            map.put(args[index], args[++index]);
            index++;
        }

        return map;
    }

    // Method checks the hashMap for key-value pairs, and stores the values in the class-fields.
    private void initValues() {

        for (String key : this.cipherMap.keySet()) {

            switch (key) {

                case MODE:
                    this.modeValue = this.cipherMap.get(MODE);
                    break;

                case KEY:
                    this.keyValue = this.cipherMap.get(KEY);
                    break;

                case IN:
                    this.inValue = this.cipherMap.get(IN);
                    break;

                case OUT:
                    this.outValue = this.cipherMap.get(OUT);
                    break;

                case DATA:
                    this.dataValue = this.cipherMap.get(DATA);
                    break;

                case ALGORITHM:
                    this.algorithmValue = this.cipherMap.get(ALGORITHM);
                    break;

                default:
                    break;

            }
        }
    }

    public void runCipher() {

        // If user input doesn't contain '-mode', encrypt input text as default.
        if (!isValueExists(MODE)) {

            this.modeValue = "enc";
        }

        // If user input doesn't contain '-key', set the value to 0 as default.
        if (!isValueExists(KEY)) {

            this.keyValue = "0";
        }

        // If user input doesn't contain '-alg', use shift-algorithm as default.
        if (!isValueExists(ALGORITHM)) {

            this.algorithmValue = "shift";
        }

        // If user input doesn't contain '-mode', get input text from a file.
        if (!isValueExists(DATA)) {

            // TODO: Insert correct path to file "..src/application/data/fileName".
            try (BufferedReader br = new BufferedReader(new FileReader(this.inValue))) {

                this.dataValue = br.readLine();

            } catch (IOException e) {

                System.out.println("Error: file " + "\"" + this.inValue + "\"" + " not found: " + e.getMessage());
            }
        }

        createCipher();
    }

    // Checks if key exists in hashMap.
    private boolean isValueExists(String key) {
        return this.cipherMap.containsKey(key);
    }

    // Method manipulates the input text based on all the input that the user has given.
    private void createCipher() {

        String encryptedText = TextManipulator.createText(this.algorithmValue, this.modeValue, this.dataValue,
                Integer.parseInt(this.keyValue));

        if (encryptedText != null) {

            // If user inputs an output-file to write the manipulated text to, the text will be written to that file;
            // else it will be written to the console.
            if (isValueExists(OUT)) {

                // TODO: Insert correct path to file "..src/application/data/fileName".
                try (BufferedWriter output = new BufferedWriter(new FileWriter(this.outValue))) {

                    output.write(encryptedText);

                } catch (IOException e) {

                    System.out.println("Error: could not write to file " + "\"" + this.outValue + "\": " + e.getMessage());
                }

            } else {

                System.out.println(encryptedText);
            }

        } else {

            System.out.println("Algorithm not found");
        }
    }
}
