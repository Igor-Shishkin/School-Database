package database;

import GUI.User;

import java.util.ArrayList;
import java.util.HashMap;

public class DataToFile {
    private ArrayList<Pupil> listOfPupils;
    private HashMap<String, User> loginInfo;

    public DataToFile() {
    }

    public DataToFile(ArrayList<Pupil> listOfPupils, HashMap<String, User> loginInfo) {
        this.listOfPupils = listOfPupils;
        this.loginInfo = loginInfo;
    }

    public ArrayList<Pupil> getListOfPupils() {
        return listOfPupils;
    }

    public void setListOfPupils(ArrayList<Pupil> listOfPupils) {
        this.listOfPupils = listOfPupils;
    }

    public HashMap<String, User> getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(HashMap<String, User> loginInfo) {
        this.loginInfo = loginInfo;
    }
}
