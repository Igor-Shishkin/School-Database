package school.database.data;

import school.database.data.objects.Pupil;
import school.database.data.objects.User;

import java.util.*;

public class DataToFile {
    private ArrayList<Pupil> listOfPupils;
    private HashMap<String, User> loginInfo;
    public DataToFile(){}

    public DataToFile(List<Pupil> listOfPupils, Map<String, User> loginInfo) {
        this.listOfPupils = (ArrayList<Pupil>) listOfPupils;
        this.loginInfo = (HashMap<String, User>) loginInfo;
    }

    public List<Pupil> getListOfPupils() {
        return listOfPupils;
    }
    public Map<String, User> getLoginInfo() {
        return loginInfo;
    }

    public void setListOfPupils(List<Pupil> listOfPupils) {
        this.listOfPupils = (ArrayList<Pupil>) listOfPupils;
    }

    public void setLoginInfo(Map<String, User> loginInfo) {
        this.loginInfo = (HashMap<String, User>) loginInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataToFile that = (DataToFile) o;
        return Objects.equals(listOfPupils, that.listOfPupils) && Objects.equals(loginInfo, that.loginInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfPupils, loginInfo);
    }
}
