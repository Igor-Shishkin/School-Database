package GUI;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    IDandPasswords() {
        loginInfo.put("1", "one");
        loginInfo.put("2", "two");
        loginInfo.put("3", "three");
        }
    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }
}
