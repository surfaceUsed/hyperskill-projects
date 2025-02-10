package org.example.util;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class CommandLineParser {

    @Parameter(names = "-t", description = "Type of client request")
    private String type;

    @Parameter(names = "-k", description = "Key-value for identifying a value in the database")
    private String key;

    @Parameter(names = "-v", description = "Value to be stored in the database")
    private String value;

    @Parameter(names = "-in", description = "Client request as a JSON-file")
    private String jsonFile;

    private CommandLineParser() {}

    public static String jsonParser(String[] args) {
        CommandLineParser clp = new CommandLineParser();
        JCommander.newBuilder()
                .addObject(clp)
                .build()
                .parse(args);
        return clp.toJson();
    }

    private String toJson() {
        if (this.jsonFile != null) {
            try {
                return readFile();
            } catch (IOException e) {
                System.out.println("Error reading json-file: " + e.getMessage());
            }
        }
        return new Gson().toJson(this);
    }

    private String readFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get(this.jsonFile)));
    }
}
