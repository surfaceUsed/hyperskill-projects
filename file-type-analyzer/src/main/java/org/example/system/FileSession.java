package org.example.system;

import org.example.util.KMP;
import org.example.util.PatternFileMapper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

class FileSession implements Callable<String> {

    private final File file;
    private final String[] pattern;

    FileSession(File file, String[] pattern) {
        this.file = file;
        this.pattern = pattern;
    }

    @Override
    public String call() throws Exception {

        String textFile = new String(readFile());

        List<FileTypeInformation> fileInfo = PatternFileMapper.sortPriority(this.pattern);

        for (FileTypeInformation info : fileInfo) {
            int indexMatch = KMP.kmp(textFile, info.getIdentifierPattern());
            if (indexMatch >= 0) {

                return this.file.getName() + ": " + info.getFileType();
            }
        }
        return this.file.getName() + ": " + "Unknown file type";
    }

    private byte[] readFile() {

        byte[] byteFile = new byte[(int) this.file.length()];

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(this.file))) {

            input.read(byteFile, 0, byteFile.length);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return byteFile;
    }
}
