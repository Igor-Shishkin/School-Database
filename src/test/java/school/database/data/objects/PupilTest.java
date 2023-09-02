package school.database.data.objects;

import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import school.database.exceptiones.WrongGradeNamberException;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class PupilTest {

    @ParameterizedTest
    @MethodSource("getIncorrectGrade")
    void setCorrectGrade(int grade) {
        Pupil pupil = new Pupil();
        ThrowableAssert.ThrowingCallable callable = () -> pupil.setGrade(grade);

        assertThatThrownBy(callable)
                .isInstanceOf(WrongGradeNamberException.class)
                .hasMessage("Class number must be between 0 and 8");
    }

    @Test
    void getGradeIdNamesSurname() {

        assertThat(PupilTest.listOfPupils.get(0).getGradeIdNamesSurname())
                .isEqualTo("2 grade.   Piotr Matuszewski.   ID: 23");
        assertThat(PupilTest.listOfPupils.get(1).getGradeIdNamesSurname())
                .isEqualTo("4 grade.   Elena Maria Czajka.   ID: 3");
    }
    @Test
    void getIdNamesSurname() {

        assertThat(PupilTest.listOfPupils.get(0).getIdNamesSurname())
                .isEqualTo("Piotr Matuszewski.    ID: 23");
        assertThat(PupilTest.listOfPupils.get(1).getIdNamesSurname())
                .isEqualTo("Elena Maria Czajka.    ID: 3");
    }
    @Test
    void getNamesAndSurname() {
        Pupil pupil = new Pupil();
        pupil.setName("Piotr");
        pupil.setSurname("Matuszewski");
        assertThat(pupil.getNamesAndSurname()).isEqualTo("Piotr Matuszewski: ");
    }

    @Test
    void getPupilInformation() {

        assertThat(PupilTest.listOfPupils.get(0).getPupilInformation())
                .isEqualTo
                        ("<html>Piotr Matuszewski<br>Date of birth: 05.03.2010<br>"
                                .concat("Grade: 2<br>Parents:<br>Jan Matuszewski. ")
                                .concat("<br>Telephone: 345123789<br>eMail: jan.matuszewski@gmail.com <br></html>"));


        assertThat(PupilTest.listOfPupils.get(1).getPupilInformation())
                .isEqualTo
                        ("<html>Elena Maria Czajka<br>Date of birth: 01.06.2014<br>"
                                .concat("Grade: 4<br>Parents:<br>Monika Czajka. ")
                                .concat("<br>Telephone: 123456789<br>eMail: monika.czajka@gmail.com <br></html>"));


        assertThat(PupilTest.listOfPupils.get(2).getPupilInformation())
                .isEqualTo
                        ("<html>Piotr Matuszewski<br>Date of birth: 05.03.2010<br>"
                                .concat("Grade: 2<br>Parents:<br>Jan Matuszewski. ")
                                .concat("<br>Telephone: 345123789<br>eMail: jan.matuszewski@gmail.com <br>")
                                .concat("Laura Matuszewski. <br>Telephone: 5555555555<br>")
                                .concat("eMail: laura.matuszewski@gmail.com <br></html>"));

        assertThat(PupilTest.listOfPupils.get(3).getPupilInformation())
                .isEqualTo
                        ("<html>Elena Maria Czajka<br>Date of birth: 01.06.2014<br>"
                                .concat("Grade: 4<br>Parents:<br>Monika Czajka. ")
                                .concat("<br>Telephone: 123456789<br>eMail: monika.czajka@gmail.com <br>")
                                .concat("Jan Czajka. <br>Telephone: 000 000 000<br>")
                                .concat("eMail: jan.czajka@gmail.com <br></html>"));
    }

    private static List<Object> getIncorrectGrade(){
        final Object[][] data = {
            {-1},
            {9},
        };
        return Arrays.asList(data);
    }
    private static List<Pupil> listOfPupils = new ArrayList<>();
    static {
        Pupil pupil1 = new Pupil();
        pupil1.setName("Piotr");
        pupil1.setSurname("Matuszewski");
        pupil1.setId(23);
        pupil1.setGrade(2);
        pupil1.setDateOfBirth(LocalDate.of(2010,3,5));
        pupil1.setParent1(new Parent("Jan", null, "Matuszewski", 'M', null,
                "12345678901", "345123789", "jan.matuszewski@gmail.com"));

        Pupil pupil2 = new Pupil();
        pupil2.setName("Elena");
        pupil2.setSecondName("Maria");
        pupil2.setSurname("Czajka");
        pupil2.setId(3);
        pupil2.setGrade(4);
        pupil2.setDateOfBirth(LocalDate.of(2014,6,1));
        pupil2.setParent1(new Parent("Monika", null, "Czajka", 'F', null,
                "22222222222", "123456789", "monika.czajka@gmail.com"));

        Pupil pupil3 = new Pupil();
        pupil3.setName("Piotr");
        pupil3.setSurname("Matuszewski");
        pupil3.setId(23);
        pupil3.setGrade(2);
        pupil3.setDateOfBirth(LocalDate.of(2010,3,5));
        pupil3.setParent1(new Parent("Jan", null, "Matuszewski", 'M', null,
                "12345678901", "345123789", "jan.matuszewski@gmail.com"));
        pupil3.setParent2(new Parent("Laura", null, "Matuszewski", 'F', null,
                "44444444444", "5555555555", "laura.matuszewski@gmail.com"));

        Pupil pupil4 = new Pupil();
        pupil4.setName("Elena");
        pupil4.setSecondName("Maria");
        pupil4.setSurname("Czajka");
        pupil4.setId(3);
        pupil4.setGrade(4);
        pupil4.setDateOfBirth(LocalDate.of(2014,6,1));
        pupil4.setParent1(new Parent("Monika", null, "Czajka", 'F', null,
                "22222222222", "123456789", "monika.czajka@gmail.com"));
        pupil4.setParent2(new Parent("Jan", null, "Czajka", 'M', null,
                "99999999999", "000 000 000", "jan.czajka@gmail.com"));

        listOfPupils.add(pupil1);
        listOfPupils.add(pupil2);
        listOfPupils.add(pupil3);
        listOfPupils.add(pupil4);
    }
}