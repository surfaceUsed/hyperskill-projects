package org.example.util;

public enum GradeLevel {

    KINDERGARTEN(1, 6),
    FIRST_GRADE(2, 7),
    SECOND_GRADE(3, 8),
    THIRD_GRADE(4, 9),
    FOURTH_GRADE(5, 10),
    FIFTH_GRADE(6, 11),
    SIXTH_GRADE(7, 12),
    SEVENTH_GRADE(8, 13),
    EIGHTH_GRADE(9, 14),
    NINTH_GRADE(10, 15),
    TENTH_GRADE(11, 16),
    ELEVENTH_GRADE(12, 17),
    TWELFTH_GRADE(13, 18),
    COLLEGE_STUDENT(14, 22);

    private final int score;
    private final int age;

    GradeLevel(int score, int age) {
        this.score = score;
        this.age = age;
    }

    public int getScore() {
        return this.score;
    }

    public int getAge() {
        return this.age;
    }

    public static int getAgeLevel(int score) {
        for (GradeLevel grade : GradeLevel.values()) {
            if (grade.getScore() == score) {
                return grade.getAge();
            }
        }
        return -1;
    }
}
