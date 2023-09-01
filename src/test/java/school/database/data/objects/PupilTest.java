package school.database.data.objects;

import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import school.database.exceptiones.WrongGradeNamberException;

import java.lang.reflect.Array;
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
    }

    @Test
    void getNamesAndSurname() {
    }

    @Test
    void getPupilInformation() {
    }

    private static List<Object> getIncorrectGrade(){
        final Object[][] data = {
            {-1},
            {9},
        };
        return Arrays.asList(data);
    }
}