package database;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        GeneratePupilData generate = new GeneratePupilData();
        Pupil pupil1 = generate.generatePupil();
        Pupil pupil2 = generate.generatePupil();
        Pupil pupil3 = generate.generatePupil();
        Pupil pupil4 = generate.generatePupil();
        Pupil pupil5 = generate.generatePupil();
        Pupil pupil6 = generate.generatePupil();

        System.out.println(pupil1);
        System.out.println(pupil2);
        System.out.println(pupil3);
        System.out.println(pupil4);
        System.out.println(pupil5);
        System.out.println(pupil6);

//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//            mapper.writeValue(new File("test.txt"), pupil1);
//            mapper.writeValue(new File("test.txt"), pupil2);
//            mapper.writeValue(new File("test.txt"), pupil3);
//            mapper.writeValue(new File("test.txt"), pupil4);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


    }
}
