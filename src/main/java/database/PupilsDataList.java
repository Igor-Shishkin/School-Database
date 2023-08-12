package database;

import com.sun.nio.sctp.MessageInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class PupilsDataList {
    static ArrayList<Pupil> pupilsDataList = new ArrayList<>();

    public static ArrayList<Pupil> getPupilsDataList() {
        return pupilsDataList;
    }

    public static void setPupilsDataList(ArrayList<Pupil> pupilsDataList) {
        PupilsDataList.pupilsDataList = pupilsDataList;
    }

    public static Boolean addPupilToList(Pupil p) {
        if (pupilsDataList.stream().noneMatch(pupil -> pupil.getId() == p.getId()) &&
                pupilsDataList.stream().noneMatch(pupil -> Objects.equals(pupil.getPesel(), p.getPesel()))) {
            pupilsDataList.add(p);
            return true;
        } else {
            return false;
        }
    }

    public static void removePupilFromList(int number) {
        pupilsDataList.remove(number);
    }

    public static ArrayList<Pupil> getListOfPupilsOfCertainGrade(int grade) {
        return (ArrayList<Pupil>) pupilsDataList.stream()
                .filter(pupil -> pupil.getGrade() == grade)
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

    public static String getPupilInformation(Pupil p) {
        if (p.getParent2() != null) {
            if (p.getSecondName() == null) {
                return String.format("<html>%s %s<br>" +
                                "Date of birth: %d.%d.%d  <br>" +
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
                            "Date of birth: %d.%d.%d  <br>" +
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
                            "Date of birth: %d.%d.%d  <br>" +
                            "Grade: %d<br>" +
                            "Parents:<br>" +
                            "    %s %s. <br>    Telephone: %s<br>eMail: %s <br>",
                    p.getName(), p.getSurname(),
                    p.getDateOfBirth().getDayOfMonth(), p.getDateOfBirth().getMonthValue(), p.getDateOfBirth().getYear(),
                    p.getGrade(),
                    p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                    p.getParent1().geteMail()
            );
        }
        return String.format("<html>%s %s %s<br>" +
                        "Date of birth: %d.%d.%d  <br>" +
                        "Grade: %d<br>" +
                        "Parents:<br>" +
                        "\t%s %s. <br>Telephone: %s<br>eMail: %s <br>",
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
                         .get().getId();
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
}

