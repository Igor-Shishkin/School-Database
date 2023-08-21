package GUI;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, User> loginInfo = new HashMap<>();

    IDandPasswords() {
        loginInfo.put("Director", new User("director1967", Permissions.DIRECTOR));
        loginInfo.put("teacher", new User("5643S", Permissions.TEACHER));
        loginInfo.put("mr Richardson", new User("1234", Permissions.CLASS_TEACHER_6));
        loginInfo.put("Pani Iwona", new User("5j569", Permissions.CLASS_TEACHER_7));
        }
    public HashMap<String, User> getLoginInfo() {
        return loginInfo;
    }
}
