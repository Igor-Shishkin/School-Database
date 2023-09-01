package school.database.exceptiones;

public class RequiredFieldIsEmpty extends RuntimeException{
    public RequiredFieldIsEmpty(String message) {
        super(message);
    }
}
