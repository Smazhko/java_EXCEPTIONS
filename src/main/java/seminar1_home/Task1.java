package seminar1_home;

/*
Реализуйте 3 метода, чтобы в каждом из них получить разные исключения.

Метод arrayOutOfBoundsException - Ошибка, связанная с выходом за пределы массива

Метод divisionByZero - Деление на 0

Метод numberFormatException - Ошибка преобразования строки в число

Важно: они не должны принимать никаких аргументов
 */


class Answer1 {
    public static void arrayOutOfBoundsException() {
        int[] array = new int[10];
        array[11] = 0;

    }

    public static void divisionByZero() {
        int a = 10;
        int b = 0;
        int c = a/b;
    }

    public static void numberFormatException() {
        String str = "124o";
        Integer num = Integer.parseInt(str);

    }
}

public class Task1 {
    public static void main(String[] args) {
        Answer1 ans = new Answer1();
        try {
            ans.arrayOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива");
        }

        try {
            ans.divisionByZero();
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        }

        try {
            ans.numberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования строки в число");
        }
    }
}