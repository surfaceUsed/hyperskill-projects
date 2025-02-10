package org.example.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryLoader extends Directory {

    private final Path filePath;

    DirectoryLoader(String directoryPath) {
        this.filePath = Paths.get(directoryPath);
    }

    boolean isDirectoryValid() {
        Path directoryPath = this.filePath.getParent();
        return Files.exists(directoryPath) && Files.isDirectory(directoryPath);
    }

    @Override
    public void createFile() {

        if (!Files.exists(this.filePath)) {
            try {
                Files.createFile(this.filePath);
            } catch (IOException e) {
                throw new RuntimeException("Error creating file \"" + this.filePath.getFileName() + "\": " + e.getMessage());
            }

        } else {
            System.out.println("File already exists in this directory");
        }
    }

    @Override
    public String getFullPath() {
        return this.filePath.toString();
    }

    @Override
    public String getFileName() {
        return this.filePath.getFileName().toString();
    }

    @Override
    public String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @Override
    public void writeToFile(String path, String data) throws IOException {
        Files.writeString(Paths.get(path), data);
    }
}
