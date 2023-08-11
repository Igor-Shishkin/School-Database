package GUI;

import GUI.addPupil.AddPupil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static MainWindow mainWindow;
    public static void main(String[] args) throws IOException, FontFormatException {
//        IDandPasswords logInfo = new IDandPasswords();
//        new LoginPage(logInfo.getLoginInfo());
        Path workDir = Paths.get("src", "main", "resources");
        File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font menuFont = remRegular.deriveFont(Font.BOLD, 15);
        UIManager.put("Menu.font", menuFont);
        UIManager.put("MenuItem.font", menuFont);

        UIManager.put("MenuItem.background", new Color (0xdee8d5));
        UIManager.put("MenuItem.foreground", Color.black);
        UIManager.put("MenuBar.background", new Color(0xac92a6));
//        UIManager.put("Menu.foreground", Color.green);
        UIManager.put("MenuItem.opaque", true);
//        mainWindow = new MainWindow();
        new MainWindow();
    }

    public static void sleep (int time) throws InterruptedException {
        Thread.sleep(time);
    }
    static void setTitleForFrame(String text) {
        mainWindow.setTitle(text);
    }
    static void refreshFrame(String text) {
        mainWindow.repaint();
    }

}
