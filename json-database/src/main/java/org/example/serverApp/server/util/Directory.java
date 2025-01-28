package org.example.serverApp.server.util;

import java.io.IOException;

public abstract class Directory {

    public abstract void createFile(String fileName);

    public abstract String readFile(String path) throws IOException;

    public abstract String getFullPath();

    public abstract void writeToFile(String path, String data) throws IOException;

    public static Directory loadDirectory(String directoryPath) {

        DirectoryLoader loader = new DirectoryLoader(directoryPath);

        if (loader.isDirectoryValid()) {

            return loader;
        }

        throw new RuntimeException("Directory invalid");
    }
}