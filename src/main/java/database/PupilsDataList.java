package database;

import com.sun.nio.sctp.MessageInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class PupilsDataList {
    static ArrayList<Pupil> pupilsDataList;

    public static ArrayList<Pupil> getPupilsDataList() {
        return pupilsDataList;
    }

    public static void setPupilsDataList(ArrayList<Pupil> pupilsDataList) {
        PupilsDataList.pupilsDataList = pupilsDataList;
    }

    public static Boolean addPupilToList(Pupil p) {
        if (pupilsDataList.stream().noneMatch(pupil -> pupil.getId() == p.getId()) &&
                pupilsDataList.stream().noneMatch(pupil -> Objects.equals(pupil.getPesel(), p.getPesel()))) {
            return pupilsDataList.add(p);
        } else {
            return false;
        }
    }
    public static boolean removePupil(Pupil pupil) {
       return pupilsDataList.remove(pupil);
    }

    public static void removePupilFromList(int number) {
        pupilsDataList.remove(number);
    }

    public static ArrayList<Pupil> getListOfPupilsOfCertainGrade(int grade) {
        return (ArrayList<Pupil>) pupilsDataList.stream()
                .filter(pupil -> pupil.getGrade() == grade)
                .sorted((p1,p2) -> p1.getSurname().compareToIgnoreCase(p2.getSurname()))
                .collect(Collectors.toList());
    }

    public static ArrayList<Pupil> getListOfAllPupils() {
        return (ArrayList<Pupil>) pupilsDataList.stream()
                .sorted(Comparator.comparingInt(Pupil::getGrade))
                .collect(Collectors.toList());
    }

    public static String getIdNamesSurname(Pupil pupil) {
        if (pupil.getSecondName() == null) {
            return String.format("%s %s.    ID: %d", pupil.getName(), pupil.getSurname(), pupil.getId());
        } else {
            return String.format("%s %s %s.    ID: %d", pupil.getName(), pupil.getSecondName()
                    , pupil.getSurname(), pupil.getId());
        }
    }

    public static String getGradeIdNamesSurname(Pupil pupil) {
        if (pupil.getSecondName() == null) {
            return String.format("%d grade.    %s %s.   ID: %d", pupil.getGrade(), pupil.getName(), pupil.getSurname(),
                    pupil.getId());
        } else {
            return String.format("%d grade.    %s %s %s.   ID: %d", pupil.getGrade(),  pupil.getName(),
                    pupil.getSecondName(), pupil.getSurname(), pupil.getId());
        }
    }

    public static Pupil getPupilWithCertainID(int id) {
        for (Pupil pupil : pupilsDataList) {
            if (pupil.getId() == id) {
                return pupil;
            }
        }
        return null;
    }

    public static boolean isNotThereID(int id){
        return pupilsDataList.stream()
                .noneMatch(pupil -> pupil.getId()==id);
    }
    public static String getPupilInformation(Pupil p) {
        if (p.getParent2() != null) {
            if (p.getSecondName() == null) {
                return String.format("<html>%s %s<br>" +
                                "Date of birth: %02d.%02d.%d  <br>" +
                                "Grade: %d<br>" +
                                "Parents:<br>" +
                                "    %s %s. <br>    Telephone: %s<br>eMail: %s <br>" +
                                "    %s %s. <br>    Telephone: %s<br>eMail: %s <br><br></html>",
                        p.getName(), p.getSurname(),
                        p.getDateOfBirth().getDayOfMonth(), p.getDateOfBirth().getMonthValue(), p.getDateOfBirth().getYear(),
                        p.getGrade(),
                        p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                        p.getParent1().geteMail(),
                        p.getParent2().getName(), p.getParent2().getSurname(), p.getParent2().getTelephone(),
                        p.getParent2().geteMail()
                );
            }
            return String.format("<html>%s %s %s<br>" +
                            "Date of birth: %02d.%02d.%d  <br>" +
                            "Grade: %d<br>" +
                            "Parents:<br>" +
                            "\t%s %s. <br>Telephone: %s<br>eMail: %s <br>" +
                            "\t%s %s. <br>Telephone: %s<br>eMail: %s <br><br></html>",
                    p.getName(), p.getSecondName(), p.getSurname(),
                    p.getDateOfBirth().getDayOfMonth(), p.getDateOfBirth().getMonthValue(), p.getDateOfBirth().getYear(),
                    p.getGrade(),
                    p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                    p.getParent1().geteMail(),
                    p.getParent2().getName(), p.getParent2().getSurname(), p.getParent2().getTelephone(),
                    p.getParent2().geteMail()
            );
        }
        if (p.getSecondName() == null) {
            return String.format("<html>%s %s<br>" +
                            "Date of birth: %02d.%02d.%d  <br>" +
                            "Grade: %d<br>" +
                            "Parents:<br>" +
                            "    %s %s. <br>    Telephone: %s<br>eMail: %s <br></html>",
                    p.getName(), p.getSurname(),
                    p.getDateOfBirth().getDayOfMonth(), p.getDateOfBirth().getMonthValue(), p.getDateOfBirth().getYear(),
                    p.getGrade(),
                    p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                    p.getParent1().geteMail()
            );
        }
        return String.format("<html>%s %s %s<br>" +
                        "Date of birth: %02d.%02d.%d  <br>" +
                        "Grade: %d<br>" +
                        "Parents:<br>" +
                        "\t%s %s. <br>Telephone: %s<br>eMail: %s <br></html>",
                p.getName(), p.getSecondName(), p.getSurname(),
                p.getDateOfBirth().getDayOfMonth(), p.getDateOfBirth().getMonthValue(), p.getDateOfBirth().getYear(),
                p.getGrade(),
                p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                p.getParent1().geteMail()
        );
    }

    public static int getMinPossibleID() {
        if (!pupilsDataList.isEmpty()) {
            for (int i = 1;
                 i < pupilsDataList.stream()
                         .max(Comparator.comparingInt(Pupil::getId))
                         .get().getId()+5;
                 i++) {
                int finalI = i;
                if (pupilsDataList.stream()
                        .noneMatch(pupil -> pupil.getId() == finalI)) {
                    return finalI;
                }
            }
        }
        return 1;
    }
    public static ArrayList<Pupil> getPupilsWithBirthdayInThisMonth (int grade) {
        if (grade==-1) {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> pupil.getDateOfBirth().getMonth().equals(LocalDate.now().getMonth()))
                    .sorted(Comparator.comparingInt(p -> p.getDateOfBirth().getDayOfMonth()))
                    .collect(Collectors.toList());
        } else {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade() == grade)
                    .filter(pupil -> pupil.getDateOfBirth().getMonth().equals(LocalDate.now().getMonth()))
                    .sorted(Comparator.comparingInt(p -> p.getDateOfBirth().getDayOfMonth()))
                    .collect(Collectors.toList());
        }
    }
    public static ArrayList<Pupil> getNoPromotedPupilsList (int grade) {
        if (grade==-1) {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> !pupil.isPromotionToNextGrade())
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        } else {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade()==grade)
                    .filter(pupil -> !pupil.isPromotionToNextGrade())
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        }
    }
    public static ArrayList<Pupil> getPupilsWithAchievementList (int grade) {
        if (grade==-1) {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> pupil.getAchievement()!=null)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        } else {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade()==grade)
                    .filter(pupil -> pupil.getAchievement()!=null)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        }
    }
    public static ArrayList<Pupil> getPupilsWithAwardBarList (int grade) {
        if (grade==-1) {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(Pupil::isAwardBar)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        } else {
            return (ArrayList<Pupil>) pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade()==grade)
                    .filter(Pupil::isAwardBar)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        }
    }
}

