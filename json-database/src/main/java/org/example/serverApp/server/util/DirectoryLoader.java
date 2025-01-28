package org.example.serverApp.server.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DirectoryLoader extends Directory {

    private final String directoryPath;
    private String fullPath;

    DirectoryLoader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    boolean isDirectoryValid() {
        Path dirPath = Paths.get(this.directoryPath);
        return Files.exists(dirPath) && Files.isDirectory(dirPath);
    }

    @Override
    public void createFile(String fileName) {

        this.fullPath = this.directoryPath + File.separator + fileName;

        Path filePath = Paths.get(this.fullPath);
        if (!Files.exists(filePath)) {

            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Error creating file \"" + fileName + "\": " + e.getMessage());
            }

        } else {
            System.out.println("File already exists in this directory");
        }
    }

    @Override
    public String getFullPath() {
        return this.fullPath;
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