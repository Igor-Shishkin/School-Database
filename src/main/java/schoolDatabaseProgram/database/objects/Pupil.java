package schoolDatabaseProgram.database.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

public class Pupil extends Person{

    private static int idNumber=0;
    private int id;
    private int grade;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
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
        super(name, secondName, surname, gender, address, pesel);
        this.id = id;
        this.grade = grade;
        this.dateOfBirth = LocalDate.of(year, month, day);
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.achievement = achievement;
        this.marks = marks;
        this.awardBar = awardBar;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    @JsonIgnore
    public String getGradeIdNamesSurname() {
        if (this.getSecondName() == null) {
            return String.format("%d grade.   %s %s.   ID: %d", this.getGrade(), this.getName(), this.getSurname(),
                    this.getId());
        } else {
            return String.format("%d grade.   %s %s %s.   ID: %d", this.getGrade(),  this.getName(),
                    this.getSecondName(), this.getSurname(), this.getId());
        }
    }

    @JsonIgnore
    public String getNamesAndSurname() {
            return String.format("%s %s: ", this.getName(), this.getSurname());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pupil pupil = (Pupil) o;
        return id == pupil.id && grade == pupil.grade && awardBar == pupil.awardBar &&
                promotionToNextGrade == pupil.promotionToNextGrade && Objects.equals(dateOfBirth, pupil.dateOfBirth) &&
                Objects.equals(parent1, pupil.parent1) && Objects.equals(parent2, pupil.parent2) &&
                Objects.equals(achievement, pupil.achievement) && Objects.equals(marks, pupil.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, dateOfBirth, parent1, parent2, achievement, marks, awardBar, promotionToNextGrade);
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
