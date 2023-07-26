package database;

import java.util.ArrayList;
import java.util.Comparator;

public class GenerateListOfPupils {
    public static void main(String[] args) {
        GeneratePupilData generatePupilData = new GeneratePupilData();
        ArrayList<Pupil> listOfPupils = new ArrayList<>(200);
        for (int i = 0; i < 100; i++) {
            listOfPupils.add(generatePupilData.generatePupil());
        }
        WriteDataToFile writeDataToFile = new WriteDataToFile();

    }
}
