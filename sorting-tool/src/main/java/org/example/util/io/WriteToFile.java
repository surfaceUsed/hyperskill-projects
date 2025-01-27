package org.example.util.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

final class WriteToFile {

    private WriteToFile() {}

    static void writeToFile(String filePath, String text) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write(text);
        }
    }
}
