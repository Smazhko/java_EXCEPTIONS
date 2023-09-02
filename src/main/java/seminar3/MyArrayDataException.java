package seminar3;

public class MyArrayDataException extends NumberFormatException{
    public static final String MESSAGE = "Элемент массива - не число в позиции";

    public MyArrayDataException() {
        super(MESSAGE);
    }

    public MyArrayDataException(String s) {
        super(MESSAGE + ": " + s);
    }
}
