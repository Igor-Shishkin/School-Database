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
        Pupil pupil1 = new Pupil();
        Pupil pupil2 = new Pupil();
        List<Pupil> listOfPupils = new ArrayList<>(List.of(pupil1,pupil2));
        List<Pupil> expectedList = new ArrayList<>(List.of(pupil1));

        Data newData = new Data();
        newData.setPupilsDataList(listOfPupils);
        newData.removePupil(pupil2);
        assertThat(newData.getPupilsDataList())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void getListOfPupilsOfCertainGrade() {
        List<Pupil> filteredList = data.getListOfPupilsOfCertainGrade(2);
        List<Pupil> expectedList = new ArrayList<>(List.of(
                data.getPupilsDataList().get(0),
                data.getPupilsDataList().get(4),
                data.getPupilsDataList().get(2)));
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void getListOfAllPupils() {
        List<Pupil> filteredList = data.getListOfAllPupils();
        List<Pupil> expectedList = new ArrayList<>(List.of(
                data.getPupilsDataList().get(0),
                data.getPupilsDataList().get(2),
                data.getPupilsDataList().get(4),
                data.getPupilsDataList().get(1),
                data.getPupilsDataList().get(3)));
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void getPupilWithCertainID() {
        Pupil pupil = data.getPupilsDataList().get(0);
        assertThat(data.getPupilWithCertainID(23)).isEqualTo(pupil);
        assertThat(data.getPupilWithCertainID(777)).isNull();
    }

    @Test
    void isNotThereID() {
        assertThat(data.isNotThereID(12)).isTrue();
        assertThat(data.isNotThereID(13)).isFalse();
    }

    @Test
    void getNoPromotedPupilsListForAllPupils() {
        List<Pupil> filteredList = data.getNoPromotedPupilsList(-1);
        List<Pupil> expectedList = new ArrayList<>(List.of(
                data.getPupilsDataList().get(2),
                data.getPupilsDataList().get(4),
                data.getPupilsDataList().get(1),
                data.getPupilsDataList().get(3)));
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(4)
                .containsExactlyElementsOf(expectedList);
    }
    @Test
    void getNoPromotedPupilsListForCertainGrades() {
        List<Pupil> filteredList = data.getNoPromotedPupilsList(2);
        List<Pupil> expectedList = new ArrayList<>(List.of(
                data.getPupilsDataList().get(2),
                data.getPupilsDataList().get(4)));
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void getPupilsWithAchievementList() {
        Pupil pupilWithAchievement = data.getPupilsDataList().get(2);
        List<Pupil> expectedList = new ArrayList<>(List.of(pupilWithAchievement));
        List<Pupil> filteredList = data.getPupilsWithAchievementList(-1);
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void getPupilsWithAwardBarList() {
        Pupil pupilWithAwardBar = data.getPupilsDataList().get(4);
        List<Pupil> expectedList = new ArrayList<>(List.of(pupilWithAwardBar));
        List<Pupil> filteredList = data.getPupilsWithAwardBarList(-1);
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void getMinPossibleID() {
        assertThat(data.getMinPossibleID()).isEqualTo(2);
    }

    @Test
    void getPupilsWithBirthdayInThisMonth(){
        Pupil pupilWithBirthdayInThisMonth = data.getPupilsDataList().get(1);
        List<Pupil> expectedList = new ArrayList<>(List.of(pupilWithBirthdayInThisMonth));
        List<Pupil> filteredList = data.getPupilsWithBirthdayInThisMonth(-1);
        assertThat(filteredList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .containsExactlyElementsOf(expectedList);
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
        pupil4.setId(1);
        pupil5.setId(5);

        pupil5.setGrade(2);
        pupil4.setGrade(5);
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
        pupil1.setPromotionToNextGrade(true);

        List<Pupil> listOfPupils = new ArrayList<>();
        listOfPupils.add(pupil1);
        listOfPupils.add(pupil2);
        listOfPupils.add(pupil3);
        listOfPupils.add(pupil4);
        listOfPupils.add(pupil5);

        int currentMonth = LocalDate.now().getMonthValue();
        pupil2.setDateOfBirth(LocalDate.of(2000, currentMonth, 7));
        int anotherMonth;
        if (currentMonth!=1) {
            anotherMonth = currentMonth-1;
        } else {
            anotherMonth = 4;
        }
        pupil1.setDateOfBirth(LocalDate.of(2015, anotherMonth, 1));
        pupil3.setDateOfBirth(LocalDate.of(2014, anotherMonth, 2));
        pupil4.setDateOfBirth(LocalDate.of(2013, anotherMonth, 3));
        pupil5.setDateOfBirth(LocalDate.of(2012, anotherMonth, 4));

        data.setPupilsDataList(listOfPupils);

    }
}