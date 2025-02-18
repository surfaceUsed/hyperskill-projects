package tests;

import enums.TestType;

public class TestFactory {

    public static ReadabilityTest getTest(String searchWord) {
        TestType type = TestType.getTest(searchWord);
        if (type != null) {
            return switch (type) {
                case ARI -> new AutomatedReadabilityIndex();
                case SMOG -> new SMOGIndex();
                case CL -> new ColemanLiauIndex();
                case FK -> new FleschKincaldReadabilityTest();
            };
        }
        return null;
    }
}
