package seminar3;

import java.io.FileNotFoundException;

public class FileDontExistException extends FileNotFoundException {
    private static final String MESSAGE = "Обращение к несуществующему файлу";

    public FileDontExistException() {
        super(MESSAGE);
    }

    public FileDontExistException(String s) {
        super(s);
    }
}
