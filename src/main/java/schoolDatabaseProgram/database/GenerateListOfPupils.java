package schoolDatabaseProgram.database;


import schoolDatabaseProgram.database.objects.Permissions;
import schoolDatabaseProgram.database.objects.Pupil;
import schoolDatabaseProgram.database.objects.User;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GenerateListOfPupils {
    public static void main(String[] args) throws IOException {
        GeneratePupilData generatePupilData = new GeneratePupilData();
        ArrayList<Pupil> listOfPupils = new ArrayList<>(200);
        for (int i = 0; i < 150; i++) {
            listOfPupils.add(generatePupilData.generatePupil());
        }
        HashMap<String, User> loginInfo = new HashMap<>();
        loginInfo.put("Director", new User("director1967", Permissions.DIRECTOR));
        loginInfo.put("teacher", new User("5643S", Permissions.TEACHER));
        loginInfo.put("mr Richardson", new User("1234", Permissions.CLASS_TEACHER_6));
        loginInfo.put("Pani Iwona", new User("5j569", Permissions.CLASS_TEACHER_7));
        loginInfo.put("1", new User("1", Permissions.DIRECTOR));

        DataToFile data = new DataToFile(listOfPupils, loginInfo);

        WriteReadDataToFile writer = new WriteReadDataToFile();
        writer.writeDataToFile(data, new File("Data_of_pupils_school_WINGS.txt"));
    }

}
