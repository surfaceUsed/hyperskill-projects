package org.example.util;

import org.example.system.FileTypeInformation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PatternFileMapper {

    public static List<FileTypeInformation> sortPriority(String[] tab) {
        List<FileTypeInformation> files = new ArrayList<>();
        for (String text : tab) {
            String[] splitText = text.split(";");
            removeQuota(splitText);
            FileTypeInformation info = new FileTypeInformation(splitText);
            files.add(info);
        }

        Collections.sort(files);

        return files;
    }

    private static void removeQuota(String[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].startsWith("\"") && tab[i].endsWith("\"")) {
                tab[i] = tab[i].substring(1, tab[i].length() - 1);
            }
        }
    }
}
