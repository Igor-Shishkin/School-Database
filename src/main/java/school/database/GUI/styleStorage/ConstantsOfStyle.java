package school.database.GUI.styleStorage;

import school.database.GUI.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConstantsOfStyle {
    private final Path workDir = Paths.get("src", "main", "resources");
    private final File fontFile = new File(workDir.resolve("REM-Regular.ttf").toUri());
    Font THE_MAIN_FONT;

    {
        try {
            THE_MAIN_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Color COLOR_FOR_RIGHT_FORMAT = new Color(0xD2FFD2);
    private final Color COLOR_FOR_WRONG_FORMAT = new Color(0xEAD1DC);
    private final Color COLOR_NEUTRAL_FORMAT = new Color(0xBBF8E690, true);


    private final Path iconsPath = Paths.get("src", "main", "resources", "icons");
    private final URL imageURL = MainWindow.class.getResource("/src/main/resources/icons/goals.png");
    private final ImageIcon FILE_ICON = new ImageIcon(iconsPath.resolve("file.png").toString());
    private final ImageIcon INFO_ICON = new ImageIcon(iconsPath.resolve("info.png").toString());
    private final ImageIcon USER_ICON = new ImageIcon(iconsPath.resolve("user.png").toString());
    private final ImageIcon STYLE_ICON = new ImageIcon(iconsPath.resolve("style.png").toString());
    BufferedImage FLAG_IMAGE;

    {
                try {
                    FLAG_IMAGE = ImageIO.read
                            (new File(Paths.get("src", "main", "resources", "Images", "FLAG_POLAND_HORIZONTAL.png")
                                    .toUri()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
    private final List<Color> ACTUAL_SET_OF_COLORS = new ArrayList<>(15);
    private final List<Color> SET_OF_COLORS_OCEAN = new ArrayList<>(15);
    private final List<Color> SET_OF_COLORS_CONTRAST = new ArrayList<>(15);

    public ConstantsOfStyle() {
        setColors();
        setActualSetOfColors(SET_OF_COLORS_OCEAN);
    }
    private void setColors() {
        SET_OF_COLORS_OCEAN.add(new Color(0xBFFFF9));
        SET_OF_COLORS_OCEAN.add(new Color(0x0032FF));
        SET_OF_COLORS_OCEAN.add(new Color(0x01125B));
        SET_OF_COLORS_OCEAN.add(new Color(0x10FF00));
        SET_OF_COLORS_OCEAN.add(new Color(0xF8B30D));
        SET_OF_COLORS_OCEAN.add(new Color(0xA68331));
        SET_OF_COLORS_OCEAN.add(new Color(0xFF969B));
        SET_OF_COLORS_OCEAN.add(new Color(0xFA326E));
        SET_OF_COLORS_OCEAN.add(new Color(0x155D00));
        SET_OF_COLORS_OCEAN.add(new Color(0xA3FDF6));
        SET_OF_COLORS_OCEAN.add(new Color(0x8BBFFD));

        SET_OF_COLORS_CONTRAST.add(new Color(0x2C313A));
        SET_OF_COLORS_CONTRAST.add(new Color(0x40FF00));
        SET_OF_COLORS_CONTRAST.add(new Color(0xF6EF9A));
        SET_OF_COLORS_CONTRAST.add(new Color(0x003AC5));
        SET_OF_COLORS_CONTRAST.add(new Color(0x064F00));
        SET_OF_COLORS_CONTRAST.add(new Color(0x02147E));
        SET_OF_COLORS_CONTRAST.add(new Color(0x9FA3FD));
        SET_OF_COLORS_CONTRAST.add(new Color(0x4D0000));
        SET_OF_COLORS_CONTRAST.add(new Color(0x27C701));
        SET_OF_COLORS_CONTRAST.add(new Color(0xB125D3));
        SET_OF_COLORS_CONTRAST.add(new Color(0x404A57));
        SET_OF_COLORS_CONTRAST.add(new Color(0x404A57));
    }
    public void setActualSetOfColors(List<Color> listOfColors) {
        for (int i = 0; i < 11; i++) {
            ACTUAL_SET_OF_COLORS.add(i, listOfColors.get(i));
        }
    }

    public Path getWorkDir() {
        return workDir;
    }

    public File getFontFile() {
        return fontFile;
    }

    public Color getCOLOR_FOR_RIGHT_FORMAT() {
        return COLOR_FOR_RIGHT_FORMAT;
    }

    public Color getCOLOR_FOR_WRONG_FORMAT() {
        return COLOR_FOR_WRONG_FORMAT;
    }

    public Color getCOLOR_NEUTRAL_FORMAT() {
        return COLOR_NEUTRAL_FORMAT;
    }

    public Path getIconsPath() {
        return iconsPath;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public ImageIcon getFILE_ICON() {
        return FILE_ICON;
    }

    public ImageIcon getINFO_ICON() {
        return INFO_ICON;
    }

    public ImageIcon getUSER_ICON() {
        return USER_ICON;
    }

    public ImageIcon getSTYLE_ICON() {
        return STYLE_ICON;
    }

    public List<Color> getACTUAL_SET_OF_COLORS() {
        return ACTUAL_SET_OF_COLORS;
    }

    public List<Color> getSET_OF_COLORS_OCEAN() {
        return SET_OF_COLORS_OCEAN;
    }

    public List<Color> getSET_OF_COLORS_CONTRAST() {
        return SET_OF_COLORS_CONTRAST;
    }
    public Font getTHE_MAIN_FONT() {
        return THE_MAIN_FONT;
    }

    public BufferedImage getFLAG_IMAGE() {
        return FLAG_IMAGE;
    }
}
