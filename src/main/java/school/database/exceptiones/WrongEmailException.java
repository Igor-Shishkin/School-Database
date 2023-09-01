package school.database.exceptiones;

public class WrongEmailException extends RuntimeException{
    public WrongEmailException(String message) {
        super(message);
    }
}
