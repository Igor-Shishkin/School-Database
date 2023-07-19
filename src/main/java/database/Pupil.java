package database;

import database.marks.Marks;

public class Pupil extends Person{

    private final int grade;
    private final Parents parents;
    private final String achievement;
    private final Marks marks;
    private final boolean awardBar;

    public Pupil(Person person, int grade, Parents parents, String achievement, Marks marks, boolean awardBar) {
        super(person);
        this.grade = grade;
        this.parents = parents;
        this.achievement = achievement;
        this.marks = marks;
        this.awardBar = awardBar;
    }

    public Pupil(String name, String surname, int year, int month, int day, Address address, long pesel, int grade,
                 Parents parents, String achievement, Marks marks, boolean awardBar) {
        super(name, surname, year, month, day, address, pesel);
        this.grade = grade;
        this.parents = parents;
        this.achievement = achievement;
        this.marks = marks;
        this.awardBar = awardBar;
    }
}
