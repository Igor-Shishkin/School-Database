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

}

