package GUI.styleStorage;

import GUI.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConstantsOfStyle {
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
    public static Color COLOR_NEUTRAL_FORMAT = new Color(0xBBF8E690, true);


    static  Path iconsPath = Paths.get("src", "main", "resources", "icons");
    static URL imageURL = MainWindow.class.getResource("/src/main/resources/icons/goals.png");
    static public ImageIcon FILE_ICON = new ImageIcon(iconsPath.resolve("file.png").toString());
    static public ImageIcon INFO_ICON = new ImageIcon(iconsPath.resolve("info.png").toString());
    static public ImageIcon USER_ICON = new ImageIcon(iconsPath.resolve("user.png").toString());
    static public ImageIcon STYLE_ICON = new ImageIcon(iconsPath.resolve("style.png").toString());
    static  public BufferedImage FLAG_IMAGE;
            static {
                try {
                    FLAG_IMAGE = ImageIO.read
                            (new File(Paths.get("src", "main", "resources", "Images", "FLAG_POLAND_HORIZONTAL.png")
                                    .toUri()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
}
