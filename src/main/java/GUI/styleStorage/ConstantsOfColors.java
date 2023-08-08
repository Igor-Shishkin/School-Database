package GUI.styleStorage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConstantsOfColors {
    static Path workDir = Paths.get("src", "main", "resources");
    static File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
    public static Font THE_MAIN_FONT;

    static {
        try {
            THE_MAIN_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Color COLOR_FOR_RIGHT_FORMAT = new Color(0xD2FFD2);
    public static Color COLOR_FOR_WRONG_FORMAT = new Color(0xEAD1DC);
    public static Color COLOR_NEUTRAL_FORMAT = new Color(0xFFFFFF);
}
