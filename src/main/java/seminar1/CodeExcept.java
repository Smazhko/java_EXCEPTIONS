package seminar1;//Реализуйте метод, принимающий в качестве аргумента целочисленный массив и некоторое значение.
// Метод ищет в массиве заданное значение и возвращает его индекс. При этом, например:
//        если длина массива меньше некоторого заданного минимума, метод возвращает -1,
//        в качестве кода ошибки.
//        если искомый элемент не найден, метод вернет -2 в качестве кода ошибки.
//        если вместо массива пришел null, метод вернет -3
//        придумайте свои варианты исключительных ситуаций и верните соответствующие коды ошибок.
//        Напишите метод, в котором реализуйте взаимодействие с пользователем. То есть,
//        этот метод запросит искомое число у пользователя, вызовет первый, обработает
//        возвращенное значение и покажет читаемый результат пользователю. Например,
//        если вернулся -2, пользователю выведется сообщение: “Искомый элемент не найден”.

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CodeExcept {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] ints = new int[10];

        for (int i = 0; i < ints.length ; i++) {
            ints[i] = new Random().nextInt(0, 11);
        }

        System.out.println(Arrays.toString(ints));

        System.out.println("Введите число");
        Integer num = input.nextInt();

        printResult(checkNum(num, ints));

//        --------------------------------
        int[] ints1 = new int[5];

        for (int i = 0; i < ints1.length ; i++) {
            ints1[i] = new Random().nextInt(0, 11);
        }

        System.out.println(Arrays.toString(ints1));
        System.out.println("Введите число");
        num = input.nextInt();

        printResult(checkNum(num, ints1));

        //        --------------------------------
        int[] ints2 = null;

        System.out.println("Введите число");
        num = input.nextInt();

        printResult(checkNum(num, ints2));
    }


    public static Integer checkNum(int num, int[] array){
        int minLength = 10;
        if (array == null){
            return -3;
        }

        if (array.length < minLength){
            return -1;
        }

        int index = -2;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == num){
                return i;
            }
        }

        return index;
    }



    public static void printResult(int code){
        switch (code){
            case -1:
                System.out.println("Слишком маленький массив");
                break;
            case -2:
                System.out.println("Искомый элемент не найден");
                break;
            case -3:
                System.out.println("Массив - NULL");
                break;
            default:
                System.out.println("Искомый элемент на " + code + " позиции");
        }
    }

}
