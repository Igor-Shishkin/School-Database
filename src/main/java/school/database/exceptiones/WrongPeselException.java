package school.database.exceptiones;

public class WrongPeselException extends RuntimeException{

    public WrongPeselException(String message) {
        super(message);
    }
}
