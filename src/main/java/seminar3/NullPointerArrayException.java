package seminar3;

public class NullPointerArrayException extends NullPointerException{
    private static final String MESSAGE = "Обращение к NULL элементу массива";

    public NullPointerArrayException() {
        super(MESSAGE);
    }

    public NullPointerArrayException(String s) {
        super(s);
    }
}
