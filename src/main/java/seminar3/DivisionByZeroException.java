package seminar3;

public class DivisionByZeroException extends ArithmeticException {
    private static final String MESSAGE = "Деление на ноль";

    public DivisionByZeroException() {
        super(MESSAGE);
    }

    public DivisionByZeroException(String s) {
        super(s);
    }
}
