package org.example.system;

public class FileTypeInformation implements Comparable<FileTypeInformation> {

    private final int priority;
    private final String identifierPattern;
    private final String fileType;

    public FileTypeInformation(String[] fileInformation) {
        this.priority = Integer.parseInt(fileInformation[0]);
        this.identifierPattern = fileInformation[1];
        this.fileType = fileInformation[2];
    }

    public int getPriority() {
        return priority;
    }

    public String getIdentifierPattern() {
        return identifierPattern;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return "{priority=" + this.priority + ", id=" + this.identifierPattern +", type=" + this.fileType +"}";
    }

    @Override
    public int compareTo(FileTypeInformation o) {
        if (this.priority > o.getPriority()) {
            return -1;
        } else if (this.priority < o.getPriority()) {
            return 1;
        }
        return 0;
    }
}
