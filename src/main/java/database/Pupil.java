package database;

import database.marks.Marks;

public class Pupil extends Person{

    private static int idNumber=0;
    private final int id;
    private int grade;
    private Parents parents;
    private String achievement;
    private Marks marks;
    private boolean awardBar;
    private boolean promotionToNextGrade;

    public Pupil(Person person, int grade, Parents parents, String achievement, Marks marks, boolean awardBar,
                 boolean promotionToNextGrade) {
        super(person.getName(),person.getSecondName(), person.getSurname(), person.getGender(), person.getYear(),
                person.getMonth(), person.getDay(), person.getAddress(), person.getPesel());
        this.id = getIdNumber();
        this.grade = grade;
        this.parents = parents;
        this.achievement = achievement;
        this.marks = marks;
        this.awardBar = awardBar;
        this.promotionToNextGrade = promotionToNextGrade;
    }

    public Pupil(String name, String surname, char gender, int year, int month, int day, Address address, String pesel, int id, int grade,
                 Parents parents, String achievement, Marks marks, boolean awardBar, boolean promotionToNextGrade) {
        super(name, surname, gender, year, month, day, address, pesel);
        this.id = getIdNumber();
        this.grade = grade;
        this.parents = parents;
        this.achievement = achievement;
        this.marks = marks;
        this.awardBar = awardBar;
        this.promotionToNextGrade = promotionToNextGrade;
    }

    public Pupil(String name, String surname, char gender, String pesel, int id, int grade, boolean promotionToNextGrade) {
        super(name, surname, gender, 0, 0, 0, null, pesel);
        this.id = getIdNumber();
        this.grade = grade;
        this.parents = null;
        this.achievement = null;
        this.marks = null;
        this.awardBar = false;
        this.promotionToNextGrade = promotionToNextGrade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public boolean isAwardBar() {
        return awardBar;
    }

    public void setAwardBar(boolean awardBar) {
        this.awardBar = awardBar;
    }

    public boolean isPromotionToNextGrade() {
        return promotionToNextGrade;
    }

    public void setPromotionToNextGrade(boolean promotionToNextGrade) {
        this.promotionToNextGrade = promotionToNextGrade;
    }
    public static int getIdNumber() {idNumber++; return idNumber;}
    public int getId() {return this.id;}

    @Override
    public String toString() {
        return "Pupil: " +
                super.toString() +
                "\nid=" + id +
                ", grade=" + grade +
                "\nparents=" + parents +
                ", achievement='" + achievement + '\'' +
                "\nmarks=" + marks +
                "\nawardBar=" + awardBar +
                ", promotionToNextGrade=" + promotionToNextGrade +
                "}\n\n";
    }
}
