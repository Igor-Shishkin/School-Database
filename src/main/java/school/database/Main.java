package school.database;

import school.database.gui.ActualElements;
import school.database.gui.MainWindow;
import school.database.gui.styleStorage.ConstantsOfStyle;
import school.database.data.Data;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {

        Data dataList = new Data();
        ConstantsOfStyle styleConstants = new ConstantsOfStyle();
        styleConstants.setActualSetOfColors(styleConstants.getSET_OF_COLORS_OCEAN());
        ActualElements actualElements = new ActualElements();

        Path workDir = Paths.get("src", "main", "resources");
        File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
        Font remRegular = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font menuFont = remRegular.deriveFont(Font.BOLD, 15);
        UIManager.put("Menu.font", menuFont);
        UIManager.put("MenuItem.font", menuFont);

        new MainWindow(dataList, styleConstants, actualElements);

    }
}
