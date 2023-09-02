/*
Если необходимо, исправьте данный код
try {
   int d = 0;
   double catchedRes1 = intArray[8] / d;
   System.out.println("catchedRes1 = " + catchedRes1);
} catch (ArithmeticException e) {
   System.out.println("Catching exception: " + e);
}
*/

package seminar2_home;

public class Task2 {
    public static void main(String[] args) {

        int[] intArray = {5, 9, 10, 5, 1, 0, 10, 7, 20}; // выброс исключения - деление на ноль
        //int[] intArray = {5, 9, 10, 5, 1, 0, 10, 7}; // выброс исключения - выход за пределы массива

        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("ПОЙМАНО ИСКЛЮЧЕНИЕ: " + e);
        } catch (ArrayIndexOutOfBoundsException e){ // добавлен блок на случай выхода за пределы массива
            System.out.println("ПОЙМАНО ИСКЛЮЧЕНИЕ: " + e);
        }

    }

}
