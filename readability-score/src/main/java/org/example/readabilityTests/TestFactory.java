package org.example.readabilityTests;

import org.example.util.TestType;

public abstract class TestFactory {

    public static ReadabilityTest getTest(String searchword) {
        TestType type = TestType.getTest(searchword);
        if (type != null) {
            return switch (type) {
                case ARI -> new AutomatedReadabilityIndex();
                case SMOG -> new SMOGIndex();
                case CL -> new ColemanLiauIndex();
                case FK -> new FleschKincaldReadabilityTest();
                default -> null;
            };
        }
        return null;
    }
}



