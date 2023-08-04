package database;

import com.sun.nio.sctp.MessageInfo;

import java.util.ArrayList;
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
    public static void addPupilToList(Pupil p) {
        if (pupilsDataList.stream().noneMatch(pupil -> pupil.getId()==p.getId()) &&
                pupilsDataList.stream().noneMatch(pupil -> Objects.equals(pupil.getPesel(), p.getPesel()))){
            pupilsDataList.add(p);
        } else {
            System.out.println("There is already such a student in the list");
        }
    }
    public static void removePupilFromList(int number) {
        pupilsDataList.remove(number);
    }
    public static ArrayList<Pupil> getListOfPupilsOfCertainGrade(int grade) {
        return (ArrayList<Pupil>) pupilsDataList.stream()
                .filter(pupil -> pupil.getGrade()==grade)
                .collect(Collectors.toList());
    }
        public static String getIdNamesSurname (Pupil pupil){
        if (pupil.getSecondName()==null) {
            return String.format("%s %s.    ID: %d", pupil.getName(), pupil.getSurname(), pupil.getId());
        } else {
            return String.format("%s %s %s.    ID: %d", pupil.getName(), pupil.getSecondName()
                    , pupil.getSurname(), pupil.getId());
        }
    }
    public static Pupil getPupilWithCertainID (int id) {
        for (Pupil pupil : pupilsDataList) {
            if (pupil.getId()==id) {
                return pupil;
            }
        }
        return null;
    }
    public static String getPupilInformation (Pupil p) {
        if (p.getSecondName()==null) {
            return String.format("<html>%s %s<br>" +
                            "Date of birth: %d.%d.%d  <br>" +
                            "Grade: %d<br>" +
                            "Pesel: %s<br>\n" +
                            "Parents:<br>" +
                            "    %s %s. <br>    Telephone: %s<br>eMail: %s <br>" +
                            "    %s %s. <br>    Telephone: %s<br>eMail: %s <br><br></html>",
                    p.getName(), p.getSurname(),
                    p.getDay(), p.getMonth(), p.getYear(),
                    p.getGrade(),
                    p.getPesel(),
                    p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                                                                                    p.getParent1().geteMail(),
                    p.getParent2().getName(), p.getParent2().getSurname(), p.getParent2().getTelephone(),
                                                                                    p.getParent2().geteMail()
                    );
        }
        return String.format("<html>%s %s %s<br>" +
                        "Date of birth: %d.%d.%d  <br>" +
                        "Grade: %d<br>" +
                        "Pesel: %s<br>" +
                        "Parents:<br>" +
                        "\t%s %s. <br>Telephone: %s<br>eMail: %s <br>" +
                        "\t%s %s. <br>Telephone: %s<br>eMail: %s <br><br></html>",
                p.getName(), p.getSecondName(), p.getSurname(),
                p.getDay(), p.getMonth(), p.getYear(),
                p.getGrade(),
                p.getPesel(),
                p.getParent1().getName(), p.getParent1().getSurname(), p.getParent1().getTelephone(),
                                                                                    p.getParent1().geteMail(),
                p.getParent2().getName(), p.getParent2().getSurname(), p.getParent2().getTelephone(),
                                                                                    p.getParent2().geteMail()
        );
    }
}

