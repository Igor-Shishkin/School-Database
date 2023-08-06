package GUI.styleStorage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorsSets {
    public static List<Color> ACTUAL_SET_OF_COLORS;
    public static ArrayList<Color> SET_OF_COLORS_SOFT_ROSE = new ArrayList<>(10);
    public static ArrayList<Color> SET_OF_COLORS_OCEAN = new ArrayList<>(10);
    public static ArrayList<Color> SET_OF_COLORS_CONTRAST = new ArrayList<>(10);
    public static ArrayList<Color> SET_OF_COLORS_AGGRESSIVE = new ArrayList<>(10);

    public ColorsSets() {
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xFFFFFF));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xE9FAE3));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xdee8d5));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xd5c7bc));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xac92a6));

        SET_OF_COLORS_OCEAN.add(new Color(0xD9F7F6));
        SET_OF_COLORS_OCEAN.add(new Color(0x8EF5F3));
        SET_OF_COLORS_OCEAN.add(new Color(0x70C2C0));
        SET_OF_COLORS_OCEAN.add(new Color(0x447574));
        SET_OF_COLORS_OCEAN.add(new Color(0x677575));

        SET_OF_COLORS_CONTRAST.add(new Color(0x5FCDD9));
        SET_OF_COLORS_CONTRAST.add(new Color(0x04BFAD));
        SET_OF_COLORS_CONTRAST.add(new Color(0x04BF9D));
        SET_OF_COLORS_CONTRAST.add(new Color(0x027373));
        SET_OF_COLORS_CONTRAST.add(new Color(0x172026));

        SET_OF_COLORS_AGGRESSIVE.add(new Color((0xF5EF49)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0x18F54C)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0xF53D4C)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0xEC31F5)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0x4A1796)));
    }
}
