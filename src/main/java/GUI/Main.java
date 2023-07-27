package GUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        IDandPasswords logInfo = new IDandPasswords();
//        new LoginPage(logInfo.getLoginInfo());

        Font fontJMenuBar = new Font("Josefin Sans", Font.BOLD, 13);
        UIManager.put("MenuBar.font", fontJMenuBar);
        UIManager.put("MenuItem.font", fontJMenuBar);

        UIManager.put("MenuItem.background", Color.CYAN);
        UIManager.put("MenuItem.foreground", Color.red);
        UIManager.put("MenuBar.background", Color.CYAN);
        UIManager.put("MenuBar.foreground", Color.RED);
//        UIManager.put("MenuItem.opaque", true);
        new MainWindow();
    }

}
