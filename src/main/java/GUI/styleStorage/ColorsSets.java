package GUI.styleStorage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorsSets {
    public static List<Color> ACTUAL_SET_OF_COLORS = new ArrayList<>(15);
    public static ArrayList<Color> SET_OF_COLORS_SOFT_ROSE = new ArrayList<>(15);
    public static ArrayList<Color> SET_OF_COLORS_OCEAN = new ArrayList<>(15);
    public static ArrayList<Color> SET_OF_COLORS_CONTRAST = new ArrayList<>(15);
    public static ArrayList<Color> SET_OF_COLORS_AGGRESSIVE = new ArrayList<>(10);

    public static void setColors() {
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xFFFFFF));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xE9FAE3));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xdee8d5));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xd5c7bc));
        SET_OF_COLORS_SOFT_ROSE.add(new Color(0xac92a6));

        SET_OF_COLORS_OCEAN.add(new Color(0xBFFFF9));
        SET_OF_COLORS_OCEAN.add(new Color(0x99AFFF));
        SET_OF_COLORS_OCEAN.add(new Color(0x12287A));
        SET_OF_COLORS_OCEAN.add(new Color(0xFFF6DF));
        SET_OF_COLORS_OCEAN.add(new Color(0xFFDF8E));
        SET_OF_COLORS_OCEAN.add(new Color(0xA68331));
        SET_OF_COLORS_OCEAN.add(new Color(0xFF969B));
        SET_OF_COLORS_OCEAN.add(new Color(0xFA32B7));
        SET_OF_COLORS_OCEAN.add(new Color(0x3CA631));

        SET_OF_COLORS_CONTRAST.add(new Color(0x282C34));
        SET_OF_COLORS_CONTRAST.add(new Color(0x36504D));
        SET_OF_COLORS_CONTRAST.add(new Color(0xAEF8EA));
        SET_OF_COLORS_CONTRAST.add(new Color(0xC5DE5B));
        SET_OF_COLORS_CONTRAST.add(new Color(0x79B9E1));
        SET_OF_COLORS_CONTRAST.add(new Color(0x7AC5EF));
        SET_OF_COLORS_CONTRAST.add(new Color(0xF51908));
        SET_OF_COLORS_CONTRAST.add(new Color(0x0A85D7));
        SET_OF_COLORS_CONTRAST.add(new Color(0x40AAF5));
        SET_OF_COLORS_CONTRAST.add(new Color(0xB125D3));

        SET_OF_COLORS_AGGRESSIVE.add(new Color((0xF5EF49)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0x18F54C)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0xF53D4C)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0xEC31F5)));
        SET_OF_COLORS_AGGRESSIVE.add(new Color((0x4A1796)));
    }
    public static void setActualSetOfColors(ArrayList<Color> listOfColors) {
        for (int i = 0; i < 9; i++) {
            ColorsSets.ACTUAL_SET_OF_COLORS.add(i, listOfColors.get(i));
        }
    }
}
