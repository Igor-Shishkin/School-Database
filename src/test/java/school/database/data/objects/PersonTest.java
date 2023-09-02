package school.database.data.objects;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import school.database.exceptiones.RequiredFieldIsEmpty;
import school.database.exceptiones.WrongPeselException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

class PersonTest {

    @ParameterizedTest
    @MethodSource("getCorrectDataForNameAndSurname")
    void testSetCorrectName(final String name) {
        final Pupil pupil = new Pupil();
        pupil.setName(name);
        assertThat(pupil.getName()).isEqualTo(name);
    }
    @ParameterizedTest
    @MethodSource("getIncorrectDataForNameAndSurname")
    void testSetIncorrectName(final String name) {
        final Pupil pupil = new Pupil();
        final ThrowableAssert.ThrowingCallable callable = () -> pupil.setName(name);

        assertThatThrownBy(callable)
                .isInstanceOf(RequiredFieldIsEmpty.class)
                .hasMessage("No name entered");
    }

    @ParameterizedTest
    @MethodSource("getCorrectDataForNameAndSurname")
    void testSetCorrectSurname(final String name) {
        final Pupil pupil = new Pupil();
        pupil.setSurname(name);
        assertThat(pupil.getSurname()).isEqualTo(name);
    }
    @ParameterizedTest
    @MethodSource("getIncorrectDataForNameAndSurname")
    void testSetIncorrectSurname(final String name) {
        final Pupil pupil = new Pupil();
        final ThrowableAssert.ThrowingCallable callable = () -> pupil.setSurname(name);

        assertThatThrownBy(callable)
                .isInstanceOf(RequiredFieldIsEmpty.class)
                .hasMessage("No surname entered");
    }

    @ParameterizedTest
    @MethodSource("getIncorrectDataForPesel")
    void testSetIncorrectPesel(String peselNumber) {
        final Pupil pupil = new Pupil();
        final ThrowableAssert.ThrowingCallable callable = () -> pupil.setPesel(peselNumber);
        assertThatThrownBy(callable)
                .isInstanceOf(WrongPeselException.class)
                .hasMessage("Pesel number must contain 11 digits");
    }
    @Test
    void testSetPeselNull() {
        final Pupil pupil = new Pupil();
        final ThrowableAssert.ThrowingCallable callable = () -> pupil.setPesel(null);
        assertThatThrownBy(callable)
                .isInstanceOf(WrongPeselException.class)
                .hasMessage("Pesel can't be null");
    }
    @ParameterizedTest
    @MethodSource("getCorrectDataForPesel")
    void testSetCorrectPesel(String peselNumber) {
        final Pupil pupil = new Pupil();
        pupil.setPesel(peselNumber);
        assertThat(pupil.getPesel()).isEqualTo(peselNumber);
    }



    private static List<Object[]> getIncorrectDataForPesel() {
        final Object[][] data = {
                {"132345"},
                {"13234523"},
                {""},
                {"sdcd234"},
                {"123456789012"},
                {"1234567890a"},
                {"1323456789012"},
        };
        return Arrays.asList(data);
    }
    private static List<Object[]> getCorrectDataForPesel() {
        final Object[][] data = {
                {"12345678901"},
                {"45645645645"},
        };
        return Arrays.asList(data);
    }
    private static List<Object[]> getIncorrectDataForNameAndSurname() {
        final Object[][] data = {
                {null},
                {""}
        };
        return Arrays.asList(data);
    }
    private static List<Object[]> getCorrectDataForNameAndSurname() {
        final Object[][] data = {
                {"Ławniczak"},
                {"Czajka"},
                {"hbjhb"},
                {"Tylkowski"},
                {"Piotr"},
                {"Elena"}
        };
        return Arrays.asList(data);
    }

}