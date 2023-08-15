package database;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Pupil extends Person{

    private static int idNumber=0;
    private int id;
    private int grade;
    private Parent parent1;
    private Parent parent2;
    private String achievement;
    private Marks marks;
    private boolean awardBar;
    private boolean promotionToNextGrade;



    public Pupil() {
        super();
        this.id = -1;
        this.grade = -1;
        this.parent1 = null;
        this.parent2 = null;
        this.achievement = null;
        this.marks = null;
        this.awardBar = false;
        this.promotionToNextGrade = false;

    }

    public Pupil(String name, String secondName, String surname, char gender, int year, int month, int day,
                 Address address, String pesel, int id, int grade, Parent parent1, Parent parent2, String achievement,
                 Marks marks, boolean awardBar, boolean promotionToNextGrade) {
        super(name, secondName, surname, gender, year, month, day, address, pesel);
        this.id = id;
        this.grade = grade;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.achievement = achievement;
        this.marks = marks;
        this.awardBar = awardBar;
        this.promotionToNextGrade = promotionToNextGrade;
    }

    public Pupil(String name, String secondName, String surname, char gender, String pesel, int id, int grade,
                 boolean promotionToNextGrade) {
        super(name, secondName, surname, gender, 0, 0, 0, null, pesel);
        this.id = id;
        this.grade = grade;
        this.parent1 = null;
        this.parent2 = null;
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

    public Parent getParent1() {
        return parent1;
    }

    public void setParent1(Parent parent1) {
        this.parent1 = parent1;
    }

    public Parent getParent2() {
        return parent2;
    }

    public void setParent2(Parent parent2) {
        this.parent2 = parent2;
    }

    public static int getIdNumber() {idNumber++; return idNumber;}
    public int getId() {return this.id;}
    public void setId(int newID) {this.id = newID;}
    public static void setIdNumber(int idNumber) {
        Pupil.idNumber = idNumber;
    }

    @JsonIgnore
    public String getPupilInformation() {
        if (this.getParent2() != null) {
            if (this.getSecondName() == null) {
                return String.format("<html>%s %s<br>" +
                                "Date of birth: %02d.%02d.%d  <br>" +
                                "Grade: %d<br>" +
                                "Parents:<br>" +
                                "    %s %s. <br>    Telephone: %s<br>eMail: %s <br>" +
                                "    %s %s. <br>    Telephone: %s<br>eMail: %s <br><br></html>",
                        this.getName(), this.getSurname(),
                        this.getDateOfBirth().getDayOfMonth(), this.getDateOfBirth().getMonthValue(),
                        this.getDateOfBirth().getYear(),
                        this.getGrade(),
                        this.getParent1().getName(), this.getParent1().getSurname(), this.getParent1().getTelephone(),
                        this.getParent1().geteMail(),
                        this.getParent2().getName(), this.getParent2().getSurname(), this.getParent2().getTelephone(),
                        this.getParent2().geteMail()
                );
            }
            return String.format("<html>%s %s %s<br>" +
                            "Date of birth: %02d.%02d.%d  <br>" +
                            "Grade: %d<br>" +
                            "Parents:<br>" +
                            "\t%s %s. <br>Telephone: %s<br>eMail: %s <br>" +
                            "\t%s %s. <br>Telephone: %s<br>eMail: %s <br><br></html>",
                    this.getName(), this.getSecondName(), this.getSurname(),
                    this.getDateOfBirth().getDayOfMonth(), this.getDateOfBirth().getMonthValue(),
                    this.getDateOfBirth().getYear(),
                    this.getGrade(),
                    this.getParent1().getName(), this.getParent1().getSurname(), this.getParent1().getTelephone(),
                    this.getParent1().geteMail(),
                    this.getParent2().getName(), this.getParent2().getSurname(), this.getParent2().getTelephone(),
                    this.getParent2().geteMail()
            );
        }
        if (this.getSecondName() == null) {
            return String.format("<html>%s %s<br>" +
                            "Date of birth: %02d.%02d.%d  <br>" +
                            "Grade: %d<br>" +
                            "Parents:<br>" +
                            "    %s %s. <br>    Telephone: %s<br>eMail: %s <br></html>",
                    this.getName(), this.getSurname(),
                    this.getDateOfBirth().getDayOfMonth(), this.getDateOfBirth().getMonthValue(),
                    this.getDateOfBirth().getYear(),
                    this.getGrade(),
                    this.getParent1().getName(), this.getParent1().getSurname(), this.getParent1().getTelephone(),
                    this.getParent1().geteMail()
            );
        }
        return String.format("<html>%s %s %s<br>" +
                        "Date of birth: %02d.%02d.%d  <br>" +
                        "Grade: %d<br>" +
                        "Parents:<br>" +
                        "\t%s %s. <br>Telephone: %s<br>eMail: %s <br></html>",
                this.getName(), this.getSecondName(), this.getSurname(),
                this.getDateOfBirth().getDayOfMonth(), this.getDateOfBirth().getMonthValue(), this.getDateOfBirth().getYear(),
                this.getGrade(),
                this.getParent1().getName(), this.getParent1().getSurname(), this.getParent1().getTelephone(),
                this.getParent1().geteMail()
        );
    }


    @Override
    public String toString() {
        return "Pupil:\n" +
                super.toString() +
                "id=" + id +
                ", grade=" + grade +
                "\nparent1=" + parent1 +
                "\nparent2=" + parent2 +
                ", achievement='" + achievement + '\'' +
                "\nmarks=" + marks +
                "\nawardBar=" + awardBar +
                ", promotionToNextGrade=" + promotionToNextGrade +
                "\n\n";
    }
}
