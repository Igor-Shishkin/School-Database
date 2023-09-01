package school.database.exceptiones;

public class WrongGradeNamberException extends RuntimeException{
    public WrongGradeNamberException(String message) {
        super(message);
    }
}
