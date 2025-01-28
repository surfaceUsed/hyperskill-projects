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

    private String[] patternList() {

        byte[] byteFile = new byte[(int) this.patternFile.length()];

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(this.patternFile))) {

            input.read(byteFile, 0, byteFile.length);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String byteString = new String(byteFile);

        return byteString.split("[\\n]");
    }

    public void init() {

        List<FileSession> sessions = new ArrayList<>();

        if (this.fileDirectory.isDirectory()) {

            File[] files = this.fileDirectory.listFiles();

            if (files != null) {

                for (File f : files) {
                    if (f.isFile()) {
                        sessions.add(new FileSession(f, patternList()));
                    }
                }

                ExecutorService executor = Executors.newFixedThreadPool(10);

                try {

                    for (Future<String> future : executor.invokeAll(sessions)) {
                        System.out.println(future.get());
                    }

                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                } finally {
                    executor.shutdown();
                }
            }
        }
    }
}
