/*
Дан следующий код, исправьте его там, где требуется

public static void main(String[] args) throws Exception {
   try {
       int a = 90;
       int b = 3;
       System.out.println(a / b);
       printSum(23, 234);
       int[] abc = { 1, 2 };
       abc[3] = 9;
   } catch (Throwable ex) {
       System.out.println("Что-то пошло не так...");
   } catch (NullPointerException ex) {
       System.out.println("Указатель не может указывать на null!");
   } catch (IndexOutOfBoundsException ex) {
       System.out.println("Массив выходит за пределы своего размера!");
   }
}
public static void printSum(Integer a, Integer b) throws FileNotFoundException {
   System.out.println(a + b);
}

 */

package seminar2_home;

// УБРАНО import java.io.FileNotFoundException; - не используется

public class Task3 {
    public static void main(String[] args) throws Exception {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b); // выводит 30

            printSum(23, 234); // выводит 257

            int[] abc = { 1, 2 };
            abc[3] = 9; // падает исключение "Массив выходит за пределы своего размера!"
        } catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) { // блок передвинут в конец, чтобы отлавливать всё остальное
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b) { // убрано throws FileNotFoundException - тут оно невозможно
        System.out.println(a + b);
    }
}
