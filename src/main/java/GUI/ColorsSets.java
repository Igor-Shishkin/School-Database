package GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorsSets {
    java.util.List<Color> setOfColorsSoftRose = new ArrayList<>(10);
    java.util.List<Color> setOfColorsOcean = new ArrayList<>(10);
    java.util.List<Color> setOfColorsContrast = new ArrayList<>(10);
    List<Color> setOfColorsAggressive = new ArrayList<>(10);
    public ColorsSets() {
        setOfColorsSoftRose.add(new Color(0xFFFFFF));
        setOfColorsSoftRose.add(new Color(0xE9FAE3));
        setOfColorsSoftRose.add(new Color(0xdee8d5));
        setOfColorsSoftRose.add(new Color(0xd5c7bc));
        setOfColorsSoftRose.add(new Color(0xac92a6));

        setOfColorsOcean.add(new Color(0xD9F7F6));
        setOfColorsOcean.add(new Color(0x8EF5F3));
        setOfColorsOcean.add(new Color(0x70C2C0));
        setOfColorsOcean.add(new Color(0x447574));
        setOfColorsOcean.add(new Color(0x677575));

        setOfColorsContrast.add(new Color(0x5FCDD9));
        setOfColorsContrast.add(new Color(0x04BFAD));
        setOfColorsContrast.add(new Color(0x04BF9D));
        setOfColorsContrast.add(new Color(0x027373));
        setOfColorsContrast.add(new Color(0x172026));

        setOfColorsAggressive.add(new Color((0xF5EF49)));
        setOfColorsAggressive.add(new Color((0x18F54C)));
        setOfColorsAggressive.add(new Color((0xF53D4C)));
        setOfColorsAggressive.add(new Color((0xEC31F5)));
        setOfColorsAggressive.add(new Color((0x4A1796)));
    }
}
