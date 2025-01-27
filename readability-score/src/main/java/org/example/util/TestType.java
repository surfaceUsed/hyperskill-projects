package org.example.util;

public enum TestType {

    ARI("Automated Readability Index", "ARI"),
    FK("Flesch–Kincaid readability tests", "FK"),
    SMOG("Simple Measure of Gobbledygook", "SMOG"),
    CL("Coleman–Liau index", "CL");

    private final String testName;
    private final String searchKey;

    TestType(String testName, String searchKey) {
        this.testName = testName;
        this.searchKey = searchKey;
    }

    public String getTestName() {
        return this.testName;
    }

    public String getSearchKey() {
        return this.searchKey;
    }

    public static TestType getTest(String searchword) {
        for (TestType type : TestType.values()) {
            if (searchword.equals(type.getSearchKey())) {
                return type;
            }
        }
        return null;
    }

    public static String[] getSearchKeys() {
        String[] keyWords = new String[TestType.values().length];
        int index = 0;
        for (TestType key : TestType.values()) {
            keyWords[index] = key.getSearchKey();
            index++;
        }
        return keyWords;
    }

}
