package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {
//        IDandPasswords logInfo = new IDandPasswords();
//        new LoginPage(logInfo.getLoginInfo());
        Path path = Paths.get("src", "main", "resources");
        File fontFile = new File(path.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font menuFont = remRegular.deriveFont(Font.BOLD, 15);
        UIManager.put("Menu.font", menuFont);
        UIManager.put("MenuItem.font", menuFont);

        UIManager.put("MenuItem.background", new Color (0xdee8d5));
        UIManager.put("MenuItem.foreground", Color.black);
        UIManager.put("MenuBar.background", new Color (0xD5C7BC));
//        UIManager.put("Menu.foreground", Color.green);
        UIManager.put("MenuItem.opaque", true);
        new MainWindow();
    }

    public static void sleep (int time) throws InterruptedException {
        Thread.currentThread().sleep(time);
    }

}
