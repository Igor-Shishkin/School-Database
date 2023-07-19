package database;

import java.util.HashMap;

public class Marks {
    private final HashMap<Subjects, Integer[]> trimesterAndYearlyGrades;

    public Marks(HashMap<Subjects, Integer[]> trimesterAndYearlyGrades) {
        this.trimesterAndYearlyGrades = trimesterAndYearlyGrades;
    }
}
