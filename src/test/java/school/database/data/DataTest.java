package school.database.data;

import org.junit.jupiter.api.Test;
import school.database.data.objects.Parent;
import school.database.data.objects.Pupil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    void addPupilToList() {
        Pupil newPupil1 = new Pupil();
        newPupil1.setId(3);
        Pupil newPupil2 = new Pupil();
        newPupil2.setPesel("33333333333");

        assertThat(DataTest.data.addPupilToList(newPupil1)).isFalse();
        assertThat(DataTest.data.addPupilToList(newPupil2)).isFalse();
    }

    @Test
    void removePupil() {
    }

    @Test
    void getListOfPupilsOfCertainGrade() {
    }

    @Test
    void getListOfAllPupils() {
    }

    @Test
    void getPupilWithCertainID() {
    }

    @Test
    void isNotThereID() {
    }

    @Test
    void getNoPromotedPupilsList() {
    }

    @Test
    void getPupilsWithAchievementList() {
    }

    @Test
    void getPupilsWithAwardBarList() {
    }

    @Test
    void getMinPossibleID() {
    }

    @Test
    void getPupilsWithBirthdayInThisMonth() {
    }

    private static Data data = new Data();
    static {
        Pupil pupil1 = new Pupil();
        pupil1.setName("Piotr");
        pupil1.setSurname("Matuszewski");

        Pupil pupil2 = new Pupil();
        pupil2.setName("Elena");
        pupil2.setSurname("Czajka");

        Pupil pupil3 = new Pupil();
        pupil3.setName("Jan");
        pupil3.setSurname("Wilczak");

        Pupil pupil4 = new Pupil();
        pupil4.setName("Sofia");
        pupil4.setSurname("Gamp");

        Pupil pupil5 = new Pupil();
        pupil5.setName("Stanisław");
        pupil5.setSurname("Pietrzak");


        pupil5.setDateOfBirth(LocalDate.of(2014,6,1));
        pupil4.setDateOfBirth(LocalDate.of(2014,6,1));
        pupil3.setDateOfBirth(LocalDate.of(2010,3,5));
        pupil2.setDateOfBirth(LocalDate.of(2014,6,1));
        pupil1.setDateOfBirth(LocalDate.of(2010,3,5));

        pupil1.setId(23);
        pupil2.setId(3);
        pupil3.setId(13);
        pupil4.setId(4);
        pupil5.setId(5);

        pupil5.setGrade(2);
        pupil4.setGrade(4);
        pupil3.setGrade(2);
        pupil2.setGrade(4);
        pupil1.setGrade(2);

        pupil1.setPesel("12345678901");
        pupil2.setPesel("22222222222");
        pupil3.setPesel("33333333333");
        pupil4.setPesel("44444444444");
        pupil5.setPesel("55555555555");

        pupil5.setAwardBar(true);

        pupil3.setAchievement("WINNER");

        List<Pupil> listOfPupils = new ArrayList<>();
        listOfPupils.add(pupil1);
        listOfPupils.add(pupil2);
        listOfPupils.add(pupil3);
        listOfPupils.add(pupil4);
        listOfPupils.add(pupil5);

        data.setPupilsDataList(listOfPupils);

    }
}