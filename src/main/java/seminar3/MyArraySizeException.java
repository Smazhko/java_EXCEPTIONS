package seminar3;

public class MyArraySizeException extends IllegalArgumentException{

    public static final String MESSAGE = "Массив не валидных размеров: ";

    public MyArraySizeException() {
        super(MESSAGE);
    }

    public MyArraySizeException(String s) {
        super(MESSAGE + s);
    }

    public MyArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArraySizeException(Throwable cause) {
        super(cause);
    }
}
