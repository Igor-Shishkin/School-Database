package schoolDatabaseProgram.database;

import schoolDatabaseProgram.database.objects.Pupil;
import schoolDatabaseProgram.database.objects.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PupilsDataList {
    private HashMap<String, User> loginInfo = new HashMap<>();
    private List<Pupil> pupilsDataList;

    public void setDataObject(DataToFile data) {

        pupilsDataList = new ArrayList<>();
//        System.out.println(schoolData[0]);

        pupilsDataList = data.getListOfPupils();
//        pupilsDataList = (List<Pupil>) schoolData[0];
        loginInfo = data.getLoginInfo();
    }

    public HashMap<String, User> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(HashMap<String, User> loginInfo) {
        this.loginInfo = loginInfo;
    }

    public DataToFile getDataToFile() {
        return new DataToFile((ArrayList<Pupil>) getPupilsDataList(), getLoginInfo());
    }
    public List<Pupil> getPupilsDataList() {
        return pupilsDataList;
    }

    public void setPupilsDataList(ArrayList<Pupil> pupilsList) {
        pupilsDataList = pupilsList;
    }

    public Boolean addPupilToList(Pupil p) {
        if (pupilsDataList.stream().noneMatch(pupil -> pupil.getId() == p.getId()) &&
                pupilsDataList.stream().noneMatch(pupil -> Objects.equals(pupil.getPesel(), p.getPesel()))) {
            return pupilsDataList.add(p);
        } else {
            return false;
        }
    }
    public void removePupil(Pupil pupil) {
        pupilsDataList.remove(pupil);
    }


    public List<Pupil> getListOfPupilsOfCertainGrade(int grade) {
        if (pupilsDataList!=null) {
            return pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade() == grade)
                    .sorted((p1, p2) -> p1.getSurname().compareToIgnoreCase(p2.getSurname()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public ArrayList<Pupil> getListOfAllPupils() {
        return (ArrayList<Pupil>) pupilsDataList.stream()
                .sorted(Comparator.comparingInt(Pupil::getGrade))
                .collect(Collectors.toList());
    }

    public String getIdNamesSurname(Pupil pupil) {
        if (pupil.getSecondName() == null) {
            return String.format("%s %s.    ID: %d", pupil.getName(), pupil.getSurname(), pupil.getId());
        } else {
            return String.format("%s %s %s.    ID: %d", pupil.getName(), pupil.getSecondName()
                    , pupil.getSurname(), pupil.getId());
        }
    }

    public String getGradeIdNamesSurname(Pupil pupil) {
        if (pupil.getSecondName() == null) {
            return String.format("%d grade.    %s %s.   ID: %d", pupil.getGrade(), pupil.getName(), pupil.getSurname(),
                    pupil.getId());
        } else {
            return String.format("%d grade.    %s %s %s.   ID: %d", pupil.getGrade(),  pupil.getName(),
                    pupil.getSecondName(), pupil.getSurname(), pupil.getId());
        }
    }

    public Pupil getPupilWithCertainID(int id) {
        for (Pupil pupil : pupilsDataList) {
            if (pupil.getId() == id) {
                return pupil;
            }
        }
        return null;
    }

    public boolean isNotThereID(int id){
        return pupilsDataList.stream()
                .noneMatch(pupil -> pupil.getId()==id);
    }
    public String getPupilInformation(Pupil p) {
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

    public int getMinPossibleID() {
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
    public ArrayList<Pupil> getPupilsWithBirthdayInThisMonth (int grade) {
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
    public ArrayList<Pupil> getNoPromotedPupilsList (int grade) {
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
    public ArrayList<Pupil> getPupilsWithAchievementList (int grade) {
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
    public ArrayList<Pupil> getPupilsWithAwardBarList (int grade) {
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

