package school.database.data;

import school.database.data.objects.User;
import school.database.data.objects.Pupil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Data{
    private HashMap<String, User> loginInfo = new HashMap<>();
    private List<Pupil> pupilsDataList;

    public void setDataObject(DataToFile data) {
        pupilsDataList = new ArrayList<>();

        pupilsDataList = data.getListOfPupils();
        loginInfo = (HashMap<String, User>) data.getLoginInfo();
    }

    public Map<String, User> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(Map<String, User> loginInfo) {
        this.loginInfo = (HashMap<String, User>) loginInfo;
    }

    public DataToFile getDataToFile() {
        return new DataToFile( getPupilsDataList(), getLoginInfo());
    }
    public List<Pupil> getPupilsDataList() {
        return pupilsDataList;
    }

    public void setPupilsDataList(List<Pupil> pupilsList) {
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
        return Collections.emptyList();
    }

    public List<Pupil> getListOfAllPupils() {
        return pupilsDataList.stream()
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
    public List<Pupil> getNoPromotedPupilsList (int grade) {
        if (grade==-1) {
            return pupilsDataList.stream()
                    .filter(pupil -> !pupil.isPromotionToNextGrade())
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        } else {
            return pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade()==grade)
                    .filter(pupil -> !pupil.isPromotionToNextGrade())
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        }
    }
    public List<Pupil> getPupilsWithAchievementList (int grade) {
        if (grade==-1) {
            return  pupilsDataList.stream()
                    .filter(pupil -> pupil.getAchievement()!=null)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        } else {
            return  pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade()==grade)
                    .filter(pupil -> pupil.getAchievement()!=null)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        }
    }
    public List<Pupil> getPupilsWithAwardBarList (int grade) {
        if (grade==-1) {
            return  pupilsDataList.stream()
                    .filter(Pupil::isAwardBar)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        } else {
            return pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade()==grade)
                    .filter(Pupil::isAwardBar)
                    .sorted(Comparator.comparingInt(Pupil::getGrade))
                    .collect(Collectors.toList());
        }
    }
    public int getMinPossibleID() {
        if (!this.getPupilsDataList().isEmpty()) {
            int maxID = pupilsDataList.stream()
                    .max(Comparator.comparingInt(Pupil::getId))
                    .get().getId()+5;
            for (int i = 1; i < maxID; i++) {
                int finalI = i;
                if (pupilsDataList.stream()
                        .noneMatch(pupil -> pupil.getId() == finalI)) {
                    return finalI;
                }
            }
        }
        return 1;
    }
    public List<Pupil> getPupilsWithBirthdayInThisMonth (int grade) {
        if (grade==-1) {
            return pupilsDataList.stream()
                    .filter(pupil -> pupil.getDateOfBirth().getMonth().equals(LocalDate.now().getMonth()))
                    .sorted(Comparator.comparingInt(p -> p.getDateOfBirth().getDayOfMonth()))
                    .collect(Collectors.toList());
        } else {
            return pupilsDataList.stream()
                    .filter(pupil -> pupil.getGrade() == grade)
                    .filter(pupil -> pupil.getDateOfBirth().getMonth().equals(LocalDate.now().getMonth()))
                    .sorted(Comparator.comparingInt(p -> p.getDateOfBirth().getDayOfMonth()))
                    .collect(Collectors.toList());
        }
    }
}

