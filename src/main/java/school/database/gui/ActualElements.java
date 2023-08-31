package school.database.gui;

import school.database.data.objects.Permissions;
import school.database.data.objects.Pupil;

public class ActualElements {
    private Pupil currentPupil;
    private Permissions actualPermissions;
    private int currentID;
    private int currentGrade;
    String userName;

    public Pupil getCurrentPupil() {
        return currentPupil;
    }

    public void setCurrentPupil(Pupil currentPupil) {
        this.currentPupil = currentPupil;
    }

    public Permissions getActualPermissions() {
        return actualPermissions;
    }

    public void setActualPermissions(Permissions actualPermissions) {
        this.actualPermissions = actualPermissions;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
