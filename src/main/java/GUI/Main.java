package GUI;

import GUI.MainWindow;
import GUI.styleStorage.ConstantsOfStyle;
import database.PupilsDataList;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static String CURRENT_USER;
    public static void main(String[] args) throws IOException, FontFormatException {

        PupilsDataList dataList = new PupilsDataList();
        ConstantsOfStyle styleConstants = new ConstantsOfStyle();
        styleConstants.setActualSetOfColors(styleConstants.getSET_OF_COLORS_OCEAN());

        Path workDir = Paths.get("src", "main", "resources");
        File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font menuFont = remRegular.deriveFont(Font.BOLD, 15);
        UIManager.put("Menu.font", menuFont);
        UIManager.put("MenuItem.font", menuFont);

        UIManager.put("MenuItem.background", new Color (0x53802C));
        UIManager.put("MenuItem.foreground", Color.black);
        UIManager.put("MenuBar.background", new Color(0xac92a6));
        UIManager.put("Menu.foreground", Color.green);
        UIManager.put("MenuItem.opaque", true);
//        mainWindow = new MainWindow(dataList);
        new MainWindow(dataList, styleConstants);

    }

    public static void sleep (int time) throws InterruptedException {
        Thread.sleep(time);
    }

}
