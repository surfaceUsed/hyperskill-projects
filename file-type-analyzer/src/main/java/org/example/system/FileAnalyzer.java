package org.example.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileAnalyzer {

    private final File fileDirectory;
    private final File patternFile;

    public FileAnalyzer(File fileDirectory, File patternFile) {
        this.fileDirectory = fileDirectory;
        this.patternFile = patternFile;
    }

    private String[] patternList() throws IOException {

        byte[] byteFile;

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(this.patternFile))) {

            byteFile = input.readAllBytes();

        } catch (IOException e) {
            throw new IOException("Failed to parse file '" + this.patternFile.getName() + "': " + e.getMessage());
        }

        String byteString = new String(byteFile);

        return byteString.split("[\\n]");
    }

    public void init() throws IOException {

        String[] patternList = patternList();

        List<FileSession> sessions = new ArrayList<>();

        if (this.fileDirectory.isDirectory()) {

            File[] files = this.fileDirectory.listFiles();

            if (files != null) {

                for (File f : files) {
                    if (f.isFile()) {
                        sessions.add(new FileSession(f, patternList));
                    }
                }

                executeSessions(sessions);
            } else {
                System.out.println("The directory '" + this.fileDirectory.getName() + "' is empty.");
            }
        } else {
            System.out.println("The directory argument '" + this.fileDirectory.getPath() + "' is not a valid directory.");
        }
    }

    private void executeSessions(List<FileSession> sessions) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            for (Future<String> future : executor.invokeAll(sessions)) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Something went wrong when executing file analyze session: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
